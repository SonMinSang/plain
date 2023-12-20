package plain.spring.commons.security.socialLogin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Schema(description = "OauthToken, 이용약관")
public class OauthAccessTokenWithPolicies {
    @Schema(description = "Oauth access token")
    private OauthAccessToken oauthAccessToken;
    @Schema(description = "이용약관")
    private Policies policies;
}
