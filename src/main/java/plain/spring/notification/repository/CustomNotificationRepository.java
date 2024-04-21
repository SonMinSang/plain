package plain.spring.notification.repository;

import plain.spring.notification.domain.Notification;

import java.util.List;

public interface CustomNotificationRepository {
    List<Notification> findAllNotificationsByUserId(Long userId, Long lastId);
}
