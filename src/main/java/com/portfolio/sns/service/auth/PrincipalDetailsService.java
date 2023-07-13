package com.portfolio.sns.service.auth;

import com.portfolio.sns.domain.User;
import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = authRepository.findByUsername(username).orElseThrow(() -> new NullPointerException("존재 하지 않는 회원입니다."));
        return new PrincipalDetails(userEntity);
    }
}
