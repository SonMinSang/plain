package plain.spring.commons.security.socialLogin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.oauth2.sdk.ParseException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface SocialOauth {


    ResponseEntity<String> requestUserInfo(OauthAccessToken oauthAccessToken);

    OauthUser getUserInfo(ResponseEntity<String> userInfoResponse) throws IOException, ParseException;

}