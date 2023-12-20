package plain.spring.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plain.spring.notification.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>, CustomNotificationRepository {


}
