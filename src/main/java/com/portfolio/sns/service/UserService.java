package com.portfolio.sns.service;

import com.portfolio.sns.domain.User;
import com.portfolio.sns.dto.user.UserProfileDto;
import com.portfolio.sns.exception.CustomException;
import com.portfolio.sns.repository.SubscribeRepository;
import com.portfolio.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
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

    /**
     * @param userId
     * @return user
     * @methodName : profile
     * @author : rulethecode9060
     * @date : 2023.07.15
     * @description : 해당 회원 번호의 프로필 데이터를 받아옴
     */
    @Transactional(readOnly = true)
    public UserProfileDto profile(int principalId, int pageUserId){
        UserProfileDto userProfileDto = new UserProfileDto();
        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });
        userProfileDto.setPageOwnerState(principalId==pageUserId);
        userProfileDto.setUser(userEntity);
        userProfileDto.setImageCount(userEntity.getImages().size());
        int subscribeCount = subscribeRepository.subscribeCount(pageUserId);
        int subscribeState = subscribeRepository.subscribeState(principalId, pageUserId);
        userProfileDto.setSubscribeCount(subscribeCount);
        userProfileDto.setSubscribeState(subscribeState==1);
        return userProfileDto;
    }

}
