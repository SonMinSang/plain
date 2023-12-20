package plain.spring.userjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.userjob.domain.UserJob;

public interface UserJobRepository extends JpaRepository<UserJob, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM UserJob u WHERE u.user.id = :userId")
    void deleteUserJobByUserId(@Param("userId") Long userId);
}
