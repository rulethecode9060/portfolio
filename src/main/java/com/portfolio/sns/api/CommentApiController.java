package com.portfolio.sns.api;

import com.portfolio.sns.domain.Comment;
import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.dto.comment.CommentDto;
import com.portfolio.sns.dto.common.CommonResponseDto;
import com.portfolio.sns.exception.CustomAPIValidationException;
import com.portfolio.sns.exception.CustomValidationException;
import com.portfolio.sns.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Comment api controller.
 */
@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    /**
     * @param commentDto
     * @param bindingResult
     * @param principalDetails
     * @return responseEntity
     * @methodName : writeComment
     * @author : rulethecode9060
     * @date : 2023.08.04
     * @description : 댓글 작성
     */
    @PostMapping("/api/comment")
    public ResponseEntity<?> writeComment(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Comment comment = commentService.writeComment(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());
        return new ResponseEntity<>(new CommonResponseDto<>(1, "댓글 작성 성공", comment), HttpStatus.CREATED);
    }

    /**
     * @param id
     * @return responseEntity
     * @methodName : deleteComment
     * @author : rulethecode9060
     * @date : 2023.08.04
     * @description : 댓글 삭제
     */
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable int id){
        commentService.deleteComment(id);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "댓글 작성 성공", null), HttpStatus.OK);
    }

}
