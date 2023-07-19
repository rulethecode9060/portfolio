package com.portfolio.sns.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(
        uniqueConstraints = {
            @UniqueConstraint(name = "subscribe_uk", columnNames = {"fromUserId", "toUserId"})
        }
)
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 구독 PK값
     */
    private int id;

    /**
     * 구독 요청자
     */
    @ManyToOne
    @JoinColumn(name = "fromUserId") // 컬럼이름을 지정안하면 fromUser_id 로만들어지기 때문에 컬럼명 직접 지정
    private User fromUser;

    /**
     * 구독 수락자
     */
    @ManyToOne
    @JoinColumn(name = "toUserId") // 컬럼이름을 지정안하면 toUser_id 로만들어지기 때문에 컬럼명 직접 지정
    private User toUser;


    /**
     * 팔로우 일자
     */
    private LocalDateTime createDate;

    /**
     * @methodName : createDate
     * @author : rulethecode9060
     * @date : 2023.07.15
     * @description : 팔로우 일자를 현재시간값으로 초기화
     */
    @PrePersist // JPA : DB에서 INSERT되기 전 호출되는 콜백
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
