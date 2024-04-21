package plain.spring.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.notification.domain.Notification;
import plain.spring.notification.dto.NotificationResponse;
import plain.spring.notification.repository.NotificationRepository;

import java.util.List;

import static plain.spring.commons.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> findAllNotifications(Long lastId){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(UNAUTHORIZED));
        if (lastId == null || lastId == 0L){
            lastId = null;
        }
        List<Notification> notifications = notificationRepository.findAllNotificationsByUserId(Long.parseLong(userId), lastId);
        List<NotificationResponse> result = notifications.stream().map(n -> new NotificationResponse(n)).toList();
        return result;
    }
    public void updateNotificationStatus(Long notificationId){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(UNAUTHORIZED));
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new CustomException(NOTIFICATION_NOT_FOUND));
        if (notification.getReceiverId() != Long.parseLong(userId))
            throw new CustomException(UNAUTHORIZED);
        notification.read();
    }
}
