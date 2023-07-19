package com.portfolio.sns.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscribeDto {
    /**
     * 회원 번호(PK)
     */
    private int id;
    /**
     * 회원 ID
     */
    private String username;
    /**
     * 회원 프로필 이미지 URL
     */
    private String profileImageUrl;
    /**
     * 구독 상태
     */
    private Integer subscribeState;
    /**
     * 로그인한 회원과 동일 회원인지 여부
     */
    private Integer equalUserState;
}
