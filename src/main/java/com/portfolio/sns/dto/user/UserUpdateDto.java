package com.portfolio.sns.dto.user;

import com.portfolio.sns.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * The type User update dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {
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
     * 홈페이지 주소
     */
    private String website;
    /**
     * 자기소개
     */
    private String bio;
    /**
     * E-mail 주소
     */
    @NotBlank
    private String email;
    /**
     * 핸드폰 번호
     */
    @NotBlank
    private String phone;
    /**
     * 성별
     */
    @NotBlank
    private String gender;

    /**
     * @return user
     * @methodName : toEntity
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : Dto를 Entity로 변경
     */
    public User toEntity(){
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }


    /**
     * Entity를 Dto형태로 변환
     * @param user the user
     */
    public UserUpdateDto(User user){
        this.password = user.getPassword();
        this.name = user.getName();
        this.website = user.getWebsite();
        this.bio = user.getBio();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.gender = user.getGender();
    }
}
