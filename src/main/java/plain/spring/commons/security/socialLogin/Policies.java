package plain.spring.commons.security.socialLogin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Schema(description = "이용약관")
public class Policies {
    @Schema(description = "서비스 이용 약관 동의")
    private boolean termsOfService;
    @Schema(description = "개인 정보 수집 및 이용 동의")
    private boolean useOfPersonalInfo;
    @Schema(description = "마케팅 정보 수신 동의")
    private boolean receiveMarketingInfo;
}
