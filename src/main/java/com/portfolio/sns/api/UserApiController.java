package com.portfolio.sns.api;

import com.portfolio.sns.domain.User;
import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.dto.common.CommonResponseDto;
import com.portfolio.sns.dto.user.UserUpdateDto;
import com.portfolio.sns.exception.CustomAPIValidationException;
import com.portfolio.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * The type User api controller.
 */
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    /**
     * @param id
     * @param userUpdateDto
     * @param bindingResult
     * @param principalDetails
     * @return common response dto
     * @methodName : modifyUserInfo
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 회원 정보 수정 요청
     */
    @PutMapping("/api/user/{id}")
    public CommonResponseDto<?> modifyUserInfo(@PathVariable int id, @Valid UserUpdateDto userUpdateDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomAPIValidationException("회원 정보 수정 실패", errorMap);
        }
        User userEntity = userService.modify(id, userUpdateDto.toEntity());
        principalDetails.setUser(userEntity);
        return new CommonResponseDto<>(1, "회원 정보 수정 성공", new UserUpdateDto(userEntity));
    }
}
