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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"imageId", "userId"})
        }
)
public class Likes {

    /**
     * PK값
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 좋아요 누른 이미지
     */
    @ManyToOne
    @JoinColumn(name = "imageId")
    private Image image;
    /**
     * 좋아요 누른 회원
     */
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"images"})
    private User user;

    /**
     * 좋아요 등록 일자
     */
    private LocalDateTime createDate;

    /**
     * @methodName : createDate
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 좋아요 등록 일자를 현재시간값으로 초기화
     */
    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }




}
