package com.portfolio.sns.config.oauth;

import com.portfolio.sns.domain.User;
import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OAuth2DetailsService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("OAuth2 Service 탐");
//        return super.loadUser(userRequest);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> userInfo = oAuth2User.getAttributes();
        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");
        String username = "facebook_" + (String) userInfo.get("id");
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
        User userEntity = userRepository.findByUsername(username);
        if(userEntity==null){
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .name(name)
                    .role("ROLE_USER")
                    .build();
            return new PrincipalDetails(userRepository.save(user), oAuth2User.getAttributes());
        }
            return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
