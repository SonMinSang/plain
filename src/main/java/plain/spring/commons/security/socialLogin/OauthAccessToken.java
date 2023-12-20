package plain.spring.commons.security.socialLogin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Schema(description = "Oauth access token")
public class OauthAccessToken {
    @Schema(description = "Oauth provider" , allowableValues = {"kakao", "google", "apple"})
    private String provider;

    @Schema(description = "Oauth access token")
    private String accessToken;
}
