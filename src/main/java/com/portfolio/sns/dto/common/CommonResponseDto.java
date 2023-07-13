package com.portfolio.sns.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponseDto<T> {
    /**
     * 1이상(성공)/-1(실패)
     */
    private int code;
    /**
     * 메세지 텍스트
     */
    private String message;
    /**
     * 반환 데이터
     */
    private T data;
}
