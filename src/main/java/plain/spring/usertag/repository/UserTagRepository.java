package plain.spring.usertag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.usertag.domain.UserTag;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserTag u WHERE u.user.id = :userId")
    void deleteUserTagByUserId(@Param("userId") Long userId);
}
