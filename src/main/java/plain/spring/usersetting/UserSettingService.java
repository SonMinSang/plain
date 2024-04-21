package plain.spring.usersetting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.exception.ErrorCode;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.user.dto.SettingEdit;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSettingService {
    private final UserSettingRepository userSettingRepository;

    public UserSettingDTO findMyUserSetting(){
        String id = SecurityUtil.getId().orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        UserSetting userSetting = userSettingRepository.findUserSettingByUserId(Long.parseLong(id)).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserSettingDTO(userSetting);
    }

    public UserSettingDTO editMyUserSetting(SettingEdit settingEdit){
        String id = SecurityUtil.getId().orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        UserSetting userSetting = userSettingRepository.findUserSettingByUserId(Long.parseLong(id)).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        switch(settingEdit.getCategory()) {
            case "활동 알림" : userSettingRepository.editUserSettingActivityNotificationByUserId(userSetting.getId(), settingEdit.isSetting());
                break;
            case "등록 알림" : userSettingRepository.editUserSettingRegisterNotificationByUserId(userSetting.getId(), settingEdit.isSetting());
                break;
            case "서비스 알림" : userSettingRepository.editUserSettingServiceNotificationByUserId(userSetting.getId(), settingEdit.isSetting());
                break;
            case "문의 차단" : userSettingRepository.editUserSettingBlockRequestByUserId(userSetting.getId(), settingEdit.isSetting());
                break;
        }
        userSetting = userSettingRepository.findUserSettingByUserId(Long.parseLong(id)).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserSettingDTO(userSetting);
    }
}
