package plain.spring.notification.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import plain.spring.notification.domain.Notification;

import java.util.List;

import static plain.spring.notification.domain.QNotification.notification;


@Repository
@RequiredArgsConstructor
public class CustomNotificationRepositoryImpl implements CustomNotificationRepository{

    private final JPAQueryFactory queryFactory;
    public List<Notification> findAllNotificationsByUserId(Long userId, Long lastId){
        List<Notification> results = queryFactory
                    .selectFrom(notification)
                    .where(eqUserId(userId), ltLastId(lastId))
                .orderBy(notification.id.desc())
                .limit(50)
                .fetch();
        return results;
    }
    private BooleanBuilder eqUserId(Long userId){
        return new BooleanBuilder(notification.receiverId.eq(userId));
    }

    private BooleanBuilder ltLastId(Long id) {
        if (id == null)
            return new BooleanBuilder();
        return new BooleanBuilder(notification.id.lt(id));
    }
}
