package com.portfolio.sns.service;

import com.portfolio.sns.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Likes service.
 */
@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;


    /**
     * @param imageId
     * @param principalId
     * @methodName : likes
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 좋아요 등록(Service->Repository)
     */
    @Transactional
    public void likes(int imageId, int principalId){
        likesRepository.likes(imageId, principalId);
    }

    /**
     * @param imageId
     * @param principalId
     * @methodName : unLikes
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 좋아요 취소(Service->Repository)
     */
    @Transactional
    public void unLikes(int imageId, int principalId){
        likesRepository.unLikes(imageId, principalId);
    }
}
