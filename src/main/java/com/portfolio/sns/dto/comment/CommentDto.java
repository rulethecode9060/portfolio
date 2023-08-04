package com.portfolio.sns.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    /**
     * 댓글 내용
     */
    @NotBlank
    private String content;
    /**
     * 이미지 번호
     */
    @NotNull
    private int imageId;
}
