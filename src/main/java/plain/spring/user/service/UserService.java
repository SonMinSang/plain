package plain.spring.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.art.domain.Art;
import plain.spring.art.dto.ArtSummary;
import plain.spring.art.repository.ArtRepository;
import plain.spring.block.BlockRepository;
import plain.spring.comment.repository.CommentRepository;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.exception.ErrorCode;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.exhibition.dto.ExhibitionSummary;
import plain.spring.exhibitionartist.domain.ExhibitionArtist;
import plain.spring.exhibitionartist.repository.ExhibitionArtistRepository;
import plain.spring.exhibitioncomment.repository.ExhibitionCommentRepository;
import plain.spring.exhibitionlikes.repository.ExhibitionLikesRepository;
import plain.spring.follow.domain.Follow;
import plain.spring.follow.repository.FollowRepository;
import plain.spring.job.repository.JobRepository;
import plain.spring.likes.domain.Likes;
import plain.spring.likes.repository.LikesRepository;
import plain.spring.notification.dto.DeviceToken;
import plain.spring.search.domain.Search;
import plain.spring.search.repository.SearchRepository;
import plain.spring.tag.service.TagService;
import plain.spring.user.domain.User;
import plain.spring.user.dto.*;
import plain.spring.user.repository.UserRepository;
import plain.spring.userjob.domain.UserJob;
import plain.spring.userjob.repository.UserJobRepository;
import plain.spring.usertag.domain.UserTag;
import plain.spring.usertag.repository.UserTagRepository;

import java.util.List;
import java.util.stream.Collectors;

import static plain.spring.commons.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final FollowRepository followRepository;
    private final ExhibitionArtistRepository exhibitionArtistRepository;
    private final ArtRepository artRepository;
    private final SearchRepository searchRepository;
    private final TagService tagService;
    private final UserTagRepository userTagRepository;
    private final JobRepository jobRepository;
    private final UserJobRepository userJobRepository;
    private final CommentRepository commentRepository;
    private final BlockRepository blockRepository;
    private final ExhibitionCommentRepository exhibitionCommentRepository;
    private final ExhibitionLikesRepository exhibitionLikesRepository;
    public UserInfo getUserInfo(Long userId){
        String id = SecurityUtil.getId().orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (blockRepository.countBlock(Long.parseLong(id), userId) > 0){
            throw new CustomException(BLOCKED_USER);
        }

        boolean isBlockRequest = user.getUserSetting().isBlockRequest();
        if (id == null) {
            return new UserInfo(user, false, isBlockRequest);
        }
        Follow follow = followRepository.findByFollowerAndFollowing(Long.parseLong(id), userId).orElse(null);
        return new UserInfo(user, follow != null, isBlockRequest);
    }
    public UserInfo updateUserInfo(UserInfoEdit userInfoEdit){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        user.editUserInfo(userInfoEdit);
        List<UserTag> userTags = userInfoEdit.getTags().stream().map(t -> UserTag.builder().tag(tagService.createOrFindTag(t)).user(user).build()).collect(Collectors.toList());
        List<UserJob> userJobs = userInfoEdit.getJobs().stream().map(j -> UserJob.builder().job(jobRepository.findByName(j).orElseThrow(() -> new CustomException(NOT_VALID_VALUE))).user(user).build()).collect(Collectors.toList());
        userTagRepository.deleteUserTagByUserId(user.getId());
        userTagRepository.saveAll(userTags);
        userJobRepository.deleteUserJobByUserId(user.getId());
        userJobRepository.saveAll(userJobs);
        user.setUserTags(userTags);
        user.setUserJobs(userJobs);
        return new UserInfo(user);
    }


    public List<ArtSummary> getUserArts(Long userId){
        User user = userRepository.findOneWithArtsById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        List<Art> arts = user.getArts();
        List<ArtSummary> result = arts.stream().map(a -> new ArtSummary(a, user.getNickname())).collect(Collectors.toList());
        return result;
    }
    public List<ArtistSummary> getUserFollow(Long userId){
        List<Follow> follows = followRepository.findAllFollowingByFollowerId(userId);
        List<ArtistSummary> results = follows.stream().map(f -> new ArtistSummary(f.getFollowing())).collect(Collectors.toList());

        return results;
    }

    public List<ArtistSummary> getFollowRecommendation(){
        List<User> users = followRepository.findByOrderByFollowsCountDescWithArtist();
        List<ArtistSummary> results = users.stream().map(u -> new ArtistSummary(u)).collect(Collectors.toList());
        return results;

    }

    public List<ArtSummary> getUserLikes(Long userId){
        List<Likes> likes  = likesRepository.findLikesWithArtByUserId(userId);
        List<ArtSummary> result = likes.stream().map(l -> new ArtSummary(l.getArt(), l.getArt().getArtist().getNickname())).collect(Collectors.toList());
        return result;
    }

    public List<ArtSummary> getLikesRecommendation(){
        List<Art> arts = artRepository.findAllOrderByLikesCountDescWithArtist();
        List<ArtSummary> result = arts.stream()
                .map(r -> new ArtSummary(r))
                .collect(Collectors.toList());
        return result;
    }


    public List<ExhibitionSummary> getUserExhibitions(Long userId){
        List<ExhibitionArtist> exhibitionArtists  = exhibitionArtistRepository.findAllByArtistWithExhibitions(userId);
        List<ExhibitionSummary> result = exhibitionArtists.stream().map(e -> new ExhibitionSummary(e.getExhibition())).toList();
        return result;
    }

    public Slice<ArtistSummary> getArtistsByQueryAndCategory(String query, String category, int pageNo){
        PageRequest pageable = PageRequest.of(pageNo, 20);
        String userId = SecurityUtil.getId().orElse(null);
        if (userId != null){
            User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
            Search search = Search.builder()
                    .user(user)
                    .query(query)
                    .build();
            searchRepository.save(search);
        }
        Slice<User> artists = userRepository.findAllArtistsByQueryAndCategory(userId, query, category, pageable);
        Slice<ArtistSummary> result = artists.map(a -> new ArtistSummary(a));
        return result;
    }
    public UserInfo editNickname(Nickname nickname) {
        String newNickname = nickname.getNickname();
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (userRepository.existsOneByNickname(newNickname))
            throw new CustomException(ErrorCode.ALREADY_ARTIST_EXIST);
        user.editNickname(newNickname);
        return new UserInfo(user);
    }
    public void updateDeviceToken(DeviceToken deviceToken){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(UNAUTHORIZED));
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        user.editDeviceToken(deviceToken.getDeviceToken());
    }
    public void deleteUser(){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(UNAUTHORIZED));
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        likesRepository.deleteLikesByUserId(user.getId());
        followRepository.deleteAllFollowByUserId(user.getId());
        artRepository.deleteAllArtsByUserId(user.getId());
        commentRepository.deleteAllCommentByUserId(user.getId());
        exhibitionCommentRepository.deleteAllByUserId(user.getId());
        exhibitionLikesRepository.deleteLikesByUserId(user.getId());
        userRepository.deleteById(user.getId());
    }
}
