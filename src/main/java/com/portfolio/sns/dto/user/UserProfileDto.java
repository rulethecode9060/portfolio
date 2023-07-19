package com.portfolio.sns.dto.user;

import com.portfolio.sns.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {
    /**
     * 현재 프로필 페이지의 유저와 같은 유저인지 여부
     */
    private boolean pageOwnerState;

    /**
     * 게시물 갯수
     */
    private int imageCount;

    /**
     * 구독 상태
     */
    private boolean subscribeState;

    /**
     * 구독자 수
     */
    private int subscribeCount;

    /**
     * 회원 정보
     */
    private User user;
}
