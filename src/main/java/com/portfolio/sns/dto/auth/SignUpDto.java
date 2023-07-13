package com.portfolio.sns.dto.auth;

import com.portfolio.sns.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The type Sign up dto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SignUpDto {
    /**
     * 계정 ID
     */
    @NotBlank
    @Size(min = 2, max = 20)
    private String username;
    /**
     * 계정 패스워드
     */
    @NotBlank
    private String password;
    /**
     * 이름
     */
    @NotBlank
    private String name;
    /**
     * E-mail 주소
     */
    @NotBlank
    private String email;


    /**
     * @return user
     * @methodName : toEntity
     * @author : rulethecode9060
     * @date : 2023.07.13
     * @description : Dto를 Entity로 변경
     */
    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }
}
