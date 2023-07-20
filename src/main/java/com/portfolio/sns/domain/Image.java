package com.portfolio.sns.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    /**
     * PK값
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 캡션
     */
    @Column(unique = true, length = 20, nullable = false) // 계정 ID는 중복이 없어야 하므로
    private String caption;

    /**
     * 이미지 파일 URL
     */
    private String postImageUrl;

    /**
     * 작성 일자
     */
    private LocalDateTime createDate;

    /**
     * 업로드한 사람
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"images"})
    private User user;

    // 이미지 좋아요

    // 댓글


    /**
     * @methodName : createDate
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 작성 일자를 현재시간값으로 초기화
     */
    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
