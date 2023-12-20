package plain.spring.likes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.art.domain.Art;
import plain.spring.art.repository.ArtRepository;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.commons.fcm.FCMNotificationRequest;
import plain.spring.commons.fcm.FCMNotificationService;
import plain.spring.likes.domain.Likes;
import plain.spring.likes.repository.LikesRepository;
import plain.spring.notification.domain.Notification;
import plain.spring.notification.dto.NotificationResponse;
import plain.spring.notification.repository.NotificationRepository;
import plain.spring.notification.domain.NotificationType;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final ArtRepository artRepository;
    private final NotificationRepository notificationRepository;
    private final FCMNotificationService fcmNotificationService;
    public void likes(Long artId){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        User user = userRepository.findById(userId).orElseThrow();
        Art art = artRepository.findByIdWithArtist(artId).orElseThrow();

        Likes likes = likesRepository.findLikesByUserAndArt(user, art).orElse(null);
        if (likes == null){
            likesRepository.save(Likes.builder()
                    .user(user)
                    .art(art)
                    .build());
            artRepository.incrementLikesCount(art.getId());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Notification notification = Notification.builder()
                .type(NotificationType.LIKES)
                .receiverId(art.getArtist().getId())
                .sender(user)
                .art(art)
                .build();
        notificationRepository.save(notification);
        NotificationResponse response = new NotificationResponse(notification);

        FCMNotificationRequest request = FCMNotificationRequest.builder()
                .deviceToken(art.getArtist().getDeviceToken())
                .image(user.getProfileImgUrl())
                .body(notification.getBody())
                .data(objectMapper.registerModule(new JavaTimeModule()).convertValue(response, Map.class))
                .build();
        fcmNotificationService.sendNotificationByToken(request);
    }



    public void unlikes(Long artId){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        User user = userRepository.findById(userId).orElseThrow();
        Art art = artRepository.findById(artId).orElseThrow();
        Likes likes = likesRepository.findLikesByUserAndArt(user, art).orElse(null);
        if (likes != null){
            likesRepository.delete(likes);
            artRepository.decreaseLikesCount(art.getId());
        }
    }
}
