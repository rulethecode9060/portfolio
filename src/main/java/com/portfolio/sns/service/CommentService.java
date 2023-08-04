package com.portfolio.sns.service;

import com.portfolio.sns.domain.Comment;
import com.portfolio.sns.domain.Image;
import com.portfolio.sns.domain.User;
import com.portfolio.sns.dto.comment.CommentDto;
import com.portfolio.sns.exception.CustomAPIException;
import com.portfolio.sns.repository.CommentRepository;
import com.portfolio.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Comment service.
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    /**
     * @param content
     * @param imageId
     * @param userId
     * @return comment
     * @methodName : writeComment
     * @author : rulethecode9060
     * @date : 2023.08.04
     * @description : 댓글 작성(Service->Repository)
     */
    @Transactional
    public Comment writeComment(String content, int imageId, int userId){
        Image image = new Image();
        image.setId(imageId);
        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomAPIException("유저 아이디를 찾을 수 없습니다.");
        });
        Comment commentEntity = new Comment();
        commentEntity.setContent(content);
        commentEntity.setImage(image);
        commentEntity.setUser(userEntity);
        return commentRepository.save(commentEntity);
    }

    /**
     * @param id
     * @methodName : deleteComment
     * @author : rulethecode9060
     * @date : 2023.08.04
     * @description : 댓글 삭제(Service->Repository)
     */
    @Transactional
    public void deleteComment(int id){
        commentRepository.deleteById(id);
    }


}
