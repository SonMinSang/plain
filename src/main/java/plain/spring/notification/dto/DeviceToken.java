package plain.spring.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "FCM 디바이스 토큰")
@AllArgsConstructor
public class DeviceToken {
    String deviceToken;
}
