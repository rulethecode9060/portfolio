package com.portfolio.sns.domain;

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
public class Comment {
    /**
     * PK값
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 댓글 내용
     */
    @Column(length = 100, nullable = false)
    private String content;

    /**
     * 작성자
     */
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    /**
     * 댓글을 단 이미지
     */
    @JoinColumn(name = "imageId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Image image;

    /**
     * 작성 일자
     */
    private LocalDateTime createDate;





    /**
     * @methodName : createDate
     * @author : rulethecode9060
     * @date : 2023.08.04
     * @description : 작성 일자를 현재시간값으로 초기화
     */
    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
