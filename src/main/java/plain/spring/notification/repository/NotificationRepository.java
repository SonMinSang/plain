package plain.spring.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.art.domain.Art;
import plain.spring.exhibitionartist.domain.ExhibitionArtist;
import plain.spring.notification.domain.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long>, CustomNotificationRepository {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Notification n where n.art.id = :artId")
    void deleteAllByArtId(@Param("artId") Long artId);

}
