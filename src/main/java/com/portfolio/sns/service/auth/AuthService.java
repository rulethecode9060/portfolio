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


}
