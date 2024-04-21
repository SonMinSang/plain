package plain.spring.usersetting;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSettingRepository extends JpaRepository<UserSetting, Long> {

    @Query("SELECT u FROM UserSetting u WHERE u.user.id = :userId")
    Optional<UserSetting> findUserSettingByUserId(@Param("userId")Long userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("Update UserSetting u set u.activityNotification = :setting WHERE u.id = :userId")
    void editUserSettingActivityNotificationByUserId(@Param("userId")Long userId, @Param("setting") Boolean setting);
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("Update UserSetting u set u.registerNotification = :setting WHERE u.id = :userId")
    void editUserSettingRegisterNotificationByUserId(@Param("userId")Long userId, @Param("setting") Boolean setting);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("Update UserSetting u set u.serviceNotification = :setting WHERE u.id = :userId")
    void editUserSettingServiceNotificationByUserId(@Param("userId")Long userId, @Param("setting") Boolean setting);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("Update UserSetting u set u.blockRequest = :setting WHERE u.id = :userId")
    void editUserSettingBlockRequestByUserId(@Param("userId")Long userId, @Param("setting") Boolean setting);


}
