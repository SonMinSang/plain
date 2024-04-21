package plain.spring.usersetting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.user.domain.User;

@Getter
@NoArgsConstructor
@Schema(description = "유저 설정")
@AllArgsConstructor
public class UserSettingDTO {
    private boolean activityNotification;
    private boolean registerNotification;
    private boolean serviceNotification;
    private boolean blockRequest;

    public UserSettingDTO(UserSetting userSetting){
        this.activityNotification = userSetting.isActivityNotification();
        this.registerNotification = userSetting.isRegisterNotification();
        this.serviceNotification = userSetting.isServiceNotification();
        this.blockRequest = userSetting.isBlockRequest();
    }
}
