package com.portfolio.sns.service.auth;

import com.portfolio.sns.domain.User;
import com.portfolio.sns.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Auth service.
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @param user
     * @return user
     * @methodName : signup
     * @author : rulethecode9060
     * @date : 2023.07.13
     * @description : 회원 가입 요청(Service -> Repository)
     */
    public User signup(User user){
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        User userEntity = authRepository.save(user);
        return userEntity;
    }
}
