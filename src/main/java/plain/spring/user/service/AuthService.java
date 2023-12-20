package plain.spring.user.service;

import com.nimbusds.oauth2.sdk.ParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.commons.security.jwt.JwtToken;
import plain.spring.commons.security.jwt.TokenProvider;
import plain.spring.commons.security.socialLogin.*;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.commons.util.UserUtil;
import plain.spring.policy.domain.Policy;
import plain.spring.policy.repository.PolicyRepository;
import plain.spring.user.domain.Role;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;
import plain.spring.user.dto.AuthToken;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PolicyRepository policyRepository;
    private final TokenProvider tokenProvider;

    public<T extends SocialOauth> AuthToken signup(OauthAccessTokenWithPolicies oauthAccessTokenWithPolicies, SocialOauth socialOauth) throws IOException, ParseException {
        AuthToken authToken = null;
        ResponseEntity<String> userInfoResponse= socialOauth.requestUserInfo(oauthAccessTokenWithPolicies.getOauthAccessToken());
        OauthUser oauthUser = socialOauth.getUserInfo(userInfoResponse);
        String user_id = oauthUser.getId();
        User user = userRepository.findOneByOauthId(user_id).orElse(null);
        if (user == null){
            String nickname = findOwnNickname();
            User newUser = User.builder()
                    .oauthId(user_id)
                    .nickname(nickname)
                    .role(Role.ROLE_USER)
                    .build();
            userRepository.save(newUser);
            JwtToken jwtToken = tokenProvider.createToken(newUser.getId(), Role.ROLE_USER);
            Policies policies = oauthAccessTokenWithPolicies.getPolicies();
            if (!policies.isTermsOfService() || !policies.isReceiveMarketingInfo()){
            }
            Policy policy = Policy.builder()
                    .user(newUser)
                    .termsOfService(policies.isTermsOfService())
                    .receiveMarketingInfo(policies.isReceiveMarketingInfo())
                    .useOfPersonalInfo(policies.isUseOfPersonalInfo())
                    .build();
            policyRepository.save(policy);
            authToken = new AuthToken(jwtToken);
        }
        else {
            JwtToken jwtToken = tokenProvider.createToken(user.getId(), user.getRole());
            authToken = new AuthToken(jwtToken);
        }
        return authToken;
    }

    public<T extends SocialOauth> AuthToken auth(OauthAccessToken oauthAccessToken, T socialOauth) throws IOException, ParseException {
        AuthToken authToken;
        ResponseEntity<String> userInfoResponse= socialOauth.requestUserInfo(oauthAccessToken);
        OauthUser oauthUser = socialOauth.getUserInfo(userInfoResponse);
        String userId = oauthUser.getId();
        User user = userRepository.findOneByOauthId(userId).orElse(null);
        if (user == null){
            authToken = new AuthToken();
            authToken.firstLogin();
        }
        else {
            JwtToken jwtToken = tokenProvider.createToken(user.getId(), user.getRole());
            authToken = new AuthToken(jwtToken);
        }
        return authToken;
    }

    @Transactional(readOnly = true)
    public String findOwnNickname(){
        String nickname = UserUtil.makeNickname();
        while (userRepository.existsOneByNickname(nickname)){
            nickname = UserUtil.makeNickname();
        }
        return nickname;
    }

    @Transactional(readOnly = true)
    public JwtToken refresh() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
        String oauthId = springSecurityUser.getUsername();
        StringBuilder role = new StringBuilder();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            role.append(authority.getAuthority());
        }
        User user = userRepository.findById(Long.valueOf(SecurityUtil.getId().orElseThrow())).orElseThrow();
        if(!user.getRole().name().equals(role)){}
        return tokenProvider.createToken(user.getId(), user.getRole());
    }
}
