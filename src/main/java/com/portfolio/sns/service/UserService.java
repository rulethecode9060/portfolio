package com.portfolio.sns.service;

import com.portfolio.sns.domain.User;
import com.portfolio.sns.exception.CustomException;
import com.portfolio.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @param id
     * @param user
     * @return user
     * @methodName : modify
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 회원 정보 수정 요청(Service -> Repository)
     */
    @Transactional
    public User modify(int id, User user){
        User userEntity = userRepository.findById(id).orElseThrow(() -> new CustomException("존재 하지 않는 회원입니다."));
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setPassword(encPassword);
        userEntity.setName(user.getName());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setBio(user.getBio());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        return userEntity;
    }

}
