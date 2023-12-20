package plain.spring.commons.security.socialLogin.google;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import plain.spring.commons.security.socialLogin.OauthAccessToken;
import plain.spring.commons.security.socialLogin.OauthUser;
import plain.spring.commons.security.socialLogin.SocialOauth;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class GoogleOauth implements SocialOauth {

    // Stirng 값을 객체로 바꾸는 Mapper
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;


    @Override
    public ResponseEntity<String> requestUserInfo(OauthAccessToken oauthAccessToken) {
        String GOOGLE_USERINFO_REQUEST_URL="https://www.googleapis.com/oauth2/v2/userinfo";

        //header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ oauthAccessToken.getAccessToken());
        System.out.println("Authorization: " + "Bearer "+ oauthAccessToken.getAccessToken());


        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_USERINFO_REQUEST_URL,
                HttpMethod.GET,
                request,
                String.class
        );
        System.out.println("response.getBody() = " + response.getBody());
        return response;
    }

    @Override
    public OauthUser getUserInfo(ResponseEntity<String> userInfoResponse) throws IOException {
        OauthUser oauthUser = objectMapper.readValue(userInfoResponse.getBody(), OauthUser.class);
        return oauthUser;
    }
}