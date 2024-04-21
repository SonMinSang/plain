package plain.spring.art.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.art.dto.*;
import plain.spring.art.repository.ArtRepository;
import plain.spring.art.domain.Art;
import plain.spring.arttag.domain.ArtTag;
import plain.spring.arttag.repository.ArtTagRepository;
import plain.spring.comment.repository.CommentRepository;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.fcm.FCMNotification;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.commons.fcm.FCMNotificationRequest;
import plain.spring.commons.fcm.FCMNotificationService;
import plain.spring.follow.domain.Follow;
import plain.spring.follow.repository.FollowRepository;
import plain.spring.image.domain.ArtImage;
import plain.spring.image.repository.ArtImageRepository;
import plain.spring.notification.domain.Notification;
import plain.spring.notification.repository.NotificationRepository;
import plain.spring.notification.domain.NotificationType;
import plain.spring.search.domain.Search;
import plain.spring.search.repository.SearchRepository;
import plain.spring.tag.service.TagService;
import plain.spring.thumbnailimage.domain.ThumbnailImage;
import plain.spring.thumbnailimage.repository.ThumbnailImageRepository;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static plain.spring.commons.exception.ErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtService {
    private final CommentRepository commentRepository;
    private final ArtRepository artRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ThumbnailImageRepository thumbnailImageRepository;
    private final ArtImageRepository artImageRepository;
    private final ArtTagRepository artTagRepository;
    private final SearchRepository searchRepository;
    private final TagService tagService;
    private final NotificationRepository notificationRepository;
    private final FCMNotificationService fcmNotificationService;

    public ArtSummary uploadArt(ArtPost artPost){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Art art = Art.builder()
                .artist(user)
                .name(artPost.getName())
                .description(artPost.getDescription())
                .forSale(artPost.isForSale())
                .price(artPost.getPrice())
                .category(artPost.getCategory())
                .likesCount(0)
                .build();


        artRepository.save(art);
        List<ThumbnailImage> thumbnailImages = artPost.getThumbnailImageUrls().stream().map(t -> ThumbnailImage.builder().art(art).url(t).build()).collect(Collectors.toList());
        List<ArtImage> artImages = artPost.getArtImageUrls().stream().map(t -> ArtImage.builder().art(art).url(t).build()).collect(Collectors.toList());
        List<ArtTag> artTags = artPost.getArtTagList().stream().map(t -> ArtTag.builder().art(art).tag(tagService.createOrFindTag(t)).build()).collect(Collectors.toList());
        thumbnailImageRepository.saveAll(thumbnailImages);
        artImageRepository.saveAll(artImages);
        artTagRepository.saveAll(artTags);
        art.setThumbnailImageUrls(thumbnailImages);
        art.setArtImageUrls(artImages);
        art.setArtTagList(artTags);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Follow> follows = followRepository.findAllFollowerByFollowingId(user.getId());
        if (!follows.isEmpty()){
            List<Notification> notifications = new ArrayList<>();
            List<FCMNotificationRequest> requests = new ArrayList<>();
            for (Follow follow : follows){
                Notification notification = Notification.builder()
                        .type(NotificationType.FOLLOW)
                        .receiverId(follow.getFollower().getId())
                        .sender(user)
                        .art(art)
                        .build();
                notifications.add(notification);
                FCMNotification response = new FCMNotification(notification);
                if (follow.getFollower().getDeviceToken() != null){
                    FCMNotificationRequest request = FCMNotificationRequest.builder()
                            .deviceToken(follow.getFollower().getDeviceToken())
                            .body(notification.getBody())
                            .data(objectMapper.registerModule(new JavaTimeModule()).convertValue(response, Map.class))
                            .build();
                    requests.add(request);
                }
            }
            notificationRepository.saveAll(notifications);
            fcmNotificationService.sendNotificationsByToken(requests);
        }

        return new ArtSummary(art);
    }
    public ArtSummary updateArt(Long artId, ArtPost artPost){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Art art = artRepository.findByIdWithArtist(artId).orElseThrow(() -> new CustomException(ART_NOT_FOUND));
        if (Long.parseLong(userId) != art.getArtist().getId())
            throw new CustomException(UNAUTHORIZED);
        art.update(artPost);
        if (artPost.getThumbnailImageUrls() != null && !artPost.getThumbnailImageUrls().isEmpty()){
            List<ThumbnailImage> thumbnailImages = artPost.getThumbnailImageUrls().stream().map(t -> ThumbnailImage.builder().url(t).art(art).build()).collect(Collectors.toList());
            thumbnailImageRepository.deleteThumbnailImageByArtId(art.getId());
            thumbnailImageRepository.saveAll(thumbnailImages);
            art.setThumbnailImageUrls(thumbnailImages);
        }
        if (artPost.getArtImageUrls() != null && !artPost.getArtImageUrls().isEmpty()){
            List<ArtImage> artImages = artPost.getArtImageUrls().stream().map(t -> ArtImage.builder().url(t).art(art).build()).collect(Collectors.toList());
            artImageRepository.deleteArtImageByArtId(art.getId());
            artImageRepository.saveAll(artImages);
            art.setArtImageUrls(artImages);
        }
        List<ArtTag> artTags = artPost.getArtTagList().stream().map(t -> ArtTag.builder().art(art).tag(tagService.createOrFindTag(t)).build()).collect(Collectors.toList());
        artTagRepository.deleteArtTagByArtId(art.getId());
        artTagRepository.saveAll(artTags);
        return new ArtSummary(art);
    }

    public List<ArtSummaryWithLikes> getHomeArtsByCategory(String category, Long id){
        String userId = SecurityUtil.getId().orElse(null);
        List<ArtWithLikes> arts;
        if (category == null || category.equals("전체"))
            category = "";
        if (userId != null){
            arts = artRepository.findAllArtsByCategoryWithLikes(category, id, Long.valueOf(userId));
        }
        else {
            arts = artRepository.findAllArtsByCategoryWithLikes(category, id, null);
        }
        if (arts == null)
            return null;
        List<ArtSummaryWithLikes> results = arts.stream().map(ArtSummaryWithLikes::new).collect(Collectors.toList());
        return results;
    }

    public Slice<ArtSummary> getArtsByQueryAndCategory(String query, String category, int pageNo){
        PageRequest pageable = PageRequest.of(pageNo, 20, Sort.by(Sort.Direction.ASC, "likesCount"));
        if (category.equals("전체"))
            category = "";
        String userId = SecurityUtil.getId().orElse(null);
        if (userId != null){
            User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
            Search search = Search.builder()
                    .user(user)
                    .query(query)
                    .build();
            searchRepository.save(search);
        }
        Slice<Art> slice = artRepository.findArtsByQueryAndCategory(query, category, pageable);
        if (slice == null)
            return null;
        Slice<ArtSummary> result = slice.map(a -> new ArtSummary(a));
        return result;
    }

    public ArtDetail getArtById(Long artId){
        String userId = SecurityUtil.getId().orElse(null);
        ArtWithFollowAndLikes art;
        if (userId == null){
             art = artRepository.findArtByIdWithFollowAndLikes(artId, null).orElseThrow(() -> new CustomException(ART_NOT_FOUND));
        } else {
            art = artRepository.findArtByIdWithFollowAndLikes(artId, Long.parseLong(userId)).orElseThrow(() -> new CustomException(ART_NOT_FOUND));
        }
        return new ArtDetail(art);
    }
    public List<ArtSummary> getArtistsOtherArts(Long artistId, Long artId){
        List<Art> art = artRepository.findArtistsOtherArts(artistId, artId);
        List<ArtSummary> results = art.stream().map(a -> new ArtSummary(a)).collect(Collectors.toList());
        return results;
    }
    public List<ArtSummary> getSimilarOtherArts(Long artId){

        List<ArtTag> tags = artTagRepository.findArtTagWithTagByArtId(artId);
        if (tags == null || tags.isEmpty()){
            return new ArrayList<ArtSummary>();
        }

        List<Art> art = artRepository.findSimilarArts(tags.stream().map(a -> a.getTag()).collect(Collectors.toList()), artId);
        List<ArtSummary> results = art.stream().map(a -> new ArtSummary(a)).collect(Collectors.toList());
        return results;
    }

    public void deleteArtById(Long artId){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Art art = artRepository.findByIdWithArtist(artId).orElseThrow(() -> new CustomException(ART_NOT_FOUND));
        if (art.getArtist().getId() != Long.parseLong(userId))
            throw new CustomException(UNAUTHORIZED);
        commentRepository.deleteAll(commentRepository.findAllByArt(art));
        notificationRepository.deleteAllByArtId(art.getId());
        artTagRepository.deleteArtTagByArtId(art.getId());
        artImageRepository.deleteArtImageByArtId(art.getId());
        thumbnailImageRepository.deleteThumbnailImageByArtId(art.getId());
        artRepository.delete(art);
    }
}
