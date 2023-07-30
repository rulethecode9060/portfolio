package com.portfolio.sns.repository;

import com.portfolio.sns.domain.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The interface Image repository.
 */
public interface ImageRepository extends JpaRepository<Image, Integer> {
    /**
     * @param principalId
     * @param pageable
     * @return page
     * @methodName : story
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 스토리(메인) 페이지 이미지 조회
     */
    @Query(value = "SELECT * FROM image WHERE userId IN(SELECT toUserId FROM subscribe WHERE fromUserId = :principalId) ORDER BY createDate DESC", nativeQuery = true)
    Page<Image> story(@Param("principalId") int principalId, Pageable pageable);

    /**
     * @return list
     * @methodName : popular
     * @author : rulethecode9060
     * @date : 2023.07.30
     * @description : 인기 페이지 인기순으로 이미지 조회
     */
    @Query(value = "SELECT *, COUNT(*) like_count FROM image i JOIN likes l ON i.id = l.imageId GROUP BY l.imageId ORDER BY like_count DESC", nativeQuery = true)
    List<Image> popular();
}
