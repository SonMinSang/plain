package plain.spring.commons.security.socialLogin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class OauthUser {
    private String id;
    private String email;
    private String name;
}