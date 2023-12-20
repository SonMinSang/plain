package plain.spring.commons.security.socialLogin.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.oauth2.sdk.ParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import plain.spring.commons.security.socialLogin.OauthAccessToken;
import plain.spring.commons.security.socialLogin.OauthUser;
import plain.spring.commons.security.socialLogin.SocialOauth;

import static com.nimbusds.oauth2.sdk.util.JSONUtils.parseJSON;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOauth implements SocialOauth {
    private final RestTemplate restTemplate;
    public ResponseEntity<String> requestUserInfo(OauthAccessToken oauthAccessToken) {
        String KAKAO_USERINFO_REQUEST_URL="https://kapi.kakao.com/v2/user/me";

        //header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ oauthAccessToken.getAccessToken());
        System.out.println("Authorization: " + "Bearer "+ oauthAccessToken.getAccessToken());

        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_USERINFO_REQUEST_URL,
                HttpMethod.GET,
                request,
                String.class
        );
        System.out.println("response.getBody() = " + response.getBody());
        return response;
    }

    public OauthUser getUserInfo(ResponseEntity<String> userInfoResponse) throws JsonProcessingException, ParseException {
        String str = userInfoResponse.getBody();
        System.out.println(str);
        JSONObject kakao_response = (JSONObject) parseJSON(str);
        JSONObject kakao_account = (JSONObject) kakao_response.get("kakao_account");
        String id = String.valueOf((Long)kakao_response.get("id"));
        String email = (String) kakao_account.get("email");
        return OauthUser.builder()
                .id(id)
                .email(email)
                .build();
    }
}
