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
     * 회원 정보
     */
    private User user;
}
