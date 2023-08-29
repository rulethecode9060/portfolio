package com.portfolio.sns.api;

import com.portfolio.sns.domain.User;
import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.dto.common.CommonResponseDto;
import com.portfolio.sns.dto.subscribe.SubscribeDto;
import com.portfolio.sns.dto.user.UserUpdateDto;
import com.portfolio.sns.exception.CustomAPIValidationException;
import com.portfolio.sns.service.SubscribeService;
import com.portfolio.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type User api controller.
 */
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    /**
     * @param id
     * @param user      update dto
     * @param binding   result
     * @param principal details
     * @return commonResponseDto response dto
     * @methodName : modifyUserInfo
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 회원 정보 수정 요청
     */
    @PutMapping("/api/user/{id}")
    public CommonResponseDto<?> modifyUserInfo(@PathVariable int id, @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User userEntity = userService.modify(id, userUpdateDto.toEntity());
        principalDetails.setUser(userEntity);
        return new CommonResponseDto<>(1, "회원 정보 수정 성공", new UserUpdateDto(userEntity));
    }

    /**
     * @param page      user id
     * @param principal details
     * @return commonResponseDto entity
     * @methodName : subscribeList
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 구독 목록 요청
     */
    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<SubscribeDto> subscribeDtos = subscribeService.subscribeList(principalDetails.getUser().getId(), pageUserId);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "구독 리스트 가져오기 성공", subscribeDtos), HttpStatus.OK);
    }

    /**
     * @param principalId
     * @param profileImageFile
     * @param principalDetails
     * @return responseEntity
     * @methodName : profileImageUrlUpdate
     * @author : rulethecode9060
     * @date : 2023.07.31
     * @description : 프로필 이미지 변경 요청
     */
    @PutMapping("/api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User userEntity = userService.updateProfileImage(principalId, profileImageFile);
        principalDetails.setUser(userEntity);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "프로필 사진 변경 성공", null), HttpStatus.OK);
    }
}
