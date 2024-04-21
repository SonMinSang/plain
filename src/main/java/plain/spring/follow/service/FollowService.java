package plain.spring.follow.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.commons.fcm.FCMNotification;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.commons.fcm.FCMNotificationRequest;
import plain.spring.commons.fcm.FCMNotificationService;
import plain.spring.follow.domain.Follow;
import plain.spring.follow.repository.FollowRepository;
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
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final FCMNotificationService fcmNotificationService;
    public void follow(Long userId){
        Long followerId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        User follower = userRepository.findById(followerId).orElseThrow();
        User following = userRepository.findById(userId).orElseThrow();
        Follow follow = followRepository.findByFollowerAndFollowing(follower.getId(), following.getId()).orElse(null);
        if (follow == null){
            followRepository.save(Follow.builder()
                    .follower(follower)
                    .following(following)
                    .build());
            following.followed();
            ObjectMapper objectMapper = new ObjectMapper();
            Notification notification = Notification.builder()
                    .type(NotificationType.FOLLOW)
                    .receiverId(following.getId())
                    .sender(follower)
                    .build();
            notificationRepository.save(notification);
            if (following.getDeviceToken() != null){
                FCMNotification response = new FCMNotification(notification);

                FCMNotificationRequest request = FCMNotificationRequest.builder()
                        .deviceToken(following.getDeviceToken())
                        .body(notification.getBody())
                        .data(objectMapper.convertValue(response, Map.class))
                        .build();
                fcmNotificationService.sendNotificationByToken(request);
            }
        }

    }
    public void unfollow(Long userId){
        Long followerId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        User follower = userRepository.findById(followerId).orElseThrow();
        User following = userRepository.findById(userId).orElseThrow();
        Follow follow = followRepository.findByFollowerAndFollowing(follower.getId(), following.getId()).orElse(null);
        if (follow != null){
            followRepository.delete(follow);
            following.unfollowed();
        }
    }
}
