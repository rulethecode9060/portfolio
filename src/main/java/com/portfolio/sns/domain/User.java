package com.portfolio.sns.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type User.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    /**
     * PK값
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 계정 ID
     */
    @Column(unique = true, length = 20, nullable = false) // 계정 ID는 중복이 없어야 하므로
    private String username;
    /**
     * 계정 패스워드
     */
    @Column(nullable = false)
    private String password;
    /**
     * 이름
     */
    @Column(nullable = false)
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
    @Column(nullable = false)
    private String email;
    /**
     * 핸드폰 번호
     */
    private String phone;
    /**
     * 성별
     */
    private String gender;
    /**
     * 가입 일자
     */
    private LocalDateTime createDate;
    /**
     * 프로필 사진 URL
     */
    private String profileImageUrl;
    /**
     * 권한
     */
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"user"})
    private List<Image> images;

    /**
     * @methodName : createDate
     * @author : rulethecode9060
     * @date : 2023.07.13
     * @description : DB INSERT 작업 전에 createDate를 현재시간값으로 초기화
     */
    @PrePersist // DB INSERT 작업 전 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
