package plain.spring.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;
import plain.spring.commons.security.jwt.JwtToken;

@NoArgsConstructor
@Schema(description = "Authorization에 필요한 토큰들 access, refresh")
public class AuthToken {

    @Schema(description = "access token")
    public String accessToken;

    @Schema(description = "refresh token")
    public String refreshToken;

    @Schema(description = "첫 로그인인지 여부")
    public boolean isFirstLogin;

    public void firstLogin(){
        isFirstLogin = true;
    }

    public AuthToken(JwtToken jwtToken){
        this.accessToken = jwtToken.getAccessToken();
        this.refreshToken = jwtToken.getRefreshToken();
        this.isFirstLogin = false;
    }
}
