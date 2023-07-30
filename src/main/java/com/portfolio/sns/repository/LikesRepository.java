package com.portfolio.sns.repository;

import com.portfolio.sns.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * The interface Likes repository.
 */
public interface LikesRepository extends JpaRepository<Likes, Integer> {


    /**
     * @param imageId
     * @param principalId
     * @return int
     * @methodName : likes
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 좋아요 등록
     */
    @Modifying
    @Query(value = "INSERT INTO likes(imageId, userId, createDate) VALUES(:imageId, :principalId, now())", nativeQuery = true)
    int likes(@Param("imageId") int imageId, @Param("principalId") int principalId);

    /**
     * @param imageId
     * @param principalId
     * @return int
     * @methodName : unLikes
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 좋아요 취소
     */
    @Modifying
    @Query(value = "DELETE FROM likes WHERE imageId = :imageId AND userId = :principalId", nativeQuery = true)
    int unLikes(@Param("imageId") int imageId, @Param("principalId") int principalId);
}
