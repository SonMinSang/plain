package plain.spring.commons.fcm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;


@Getter
@Builder
@NoArgsConstructor
@Schema(description = "FCM 요청")
@AllArgsConstructor
public class FCMNotificationRequest {
    private String deviceToken;
    private String title;
    private String body;
    private String image;
    private Map<String, String> data;


}
