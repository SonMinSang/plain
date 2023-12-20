package plain.spring.notification.repository;

import org.springframework.data.repository.query.Param;
import plain.spring.notification.domain.Notification;

import java.util.List;

public interface CustomNotificationRepository {
    List<Notification> findAllNotificationsByUserId(Long userId, Long lastId);
}
