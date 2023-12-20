package plain.spring.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.oauth2.sdk.ParseException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import plain.spring.commons.security.jwt.JwtToken;
import plain.spring.commons.security.socialLogin.GoogleInfResponse;
import plain.spring.commons.security.socialLogin.OauthAccessToken;
import plain.spring.commons.security.socialLogin.OauthAccessTokenWithPolicies;
import plain.spring.commons.security.socialLogin.google.GoogleOauth;
import plain.spring.commons.security.socialLogin.kakao.KakaoOauth;
import plain.spring.user.dto.AuthToken;
import plain.spring.user.service.AuthService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "인증관련 API", description = "토큰 발급, 재발급, 회원가입")
public class AuthController {
    private final AuthService authService;
    private final GoogleOauth googleOauth;
    private final KakaoOauth kakaoOauth;

    @Operation(summary = "회원가입", description = "회원가입 후 access, refresh token을 발급한다.")
    @PostMapping("/signup")
    public ResponseEntity<AuthToken> signup(@RequestBody OauthAccessTokenWithPolicies oauthAccessTokenWithPolicies) throws IOException, ParseException {
        AuthToken authToken;
        String provider = oauthAccessTokenWithPolicies.getOauthAccessToken().getProvider();
        if (provider.equals("google"))
            authToken = authService.signup(oauthAccessTokenWithPolicies, googleOauth);
        else if (provider.equals("kakao"))
            authToken = authService.signup(oauthAccessTokenWithPolicies, kakaoOauth);
        else
            authToken = new AuthToken(new JwtToken());
        return ResponseEntity.ok(authToken);
    }


    @Operation(summary = "토큰 발급", description = "Provider의 access token을 통해 access, refresh token을 발급한다. 첫 번째 로그인인지 여부도 포함한다.")
    @PostMapping("/auth")
    public ResponseEntity<AuthToken> auth(@RequestBody OauthAccessToken oauthAccessToken) throws IOException, ParseException {
        AuthToken authToken;
        String provider = oauthAccessToken.getProvider();
        if (provider.equals("google"))
            authToken = authService.auth(oauthAccessToken, googleOauth);
        else if (provider.equals("kakao"))
            authToken = authService.auth(oauthAccessToken, kakaoOauth);
        else
            authToken = new AuthToken(new JwtToken());

        return ResponseEntity.ok(authToken);
    }

    @Operation(summary = "토큰 재발급", description = "refresh token을 통해 access, refresh token을 재발급한다.",
            parameters = {
                    @Parameter(in = HEADER, example = "Bearer refreshToken", name = "Authorization", description = "RefreshToken", required = true)
            }
    )
    @PostMapping("/auth/refresh")
    public JwtToken refresh(){
        JwtToken jwtToken = authService.refresh();
        return jwtToken;
    }


}
