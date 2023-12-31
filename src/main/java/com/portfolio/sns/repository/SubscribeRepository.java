package com.portfolio.sns.repository;

import com.portfolio.sns.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * The interface Subscribe repository.
 */
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
    /**
     * @param fromUserId
     * @param toUserId
     * @methodName : subscribe
     * @author : rulethecode9060
     * @date : 2023.07.16
     * @description : 구독하기(Service->Repository)
     */
    @Modifying
    @Query(value = "INSERT INTO  subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void subscribe(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);

    /**
     * @param fromUserId
     * @param toUserId
     * @methodName : unSubscribe
     * @author : rulethecode9060
     * @date : 2023.07.16
     * @description : 구독취소(Service->Repository)
     */
    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void unSubscribe(@Param("fromUserId") int fromUserId, @Param("toUserId") int toUserId);

    /**
     * @param pageUserId
     * @return int
     * @methodName : subscribeCount
     * @author : rulethecode9060
     * @date : 2023.07.18
     * @description : 현재 프로필 페이지 회원의 구독자 수를 반환
     */
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId=:pageUserId", nativeQuery = true)
    int subscribeCount(@Param("pageUserId") int pageUserId);

    /**
     * @param principalId
     * @param pageUserId
     * @return int
     * @methodName : subscribeState
     * @author : rulethecode9060
     * @date : 2023.07.18
     * @description : 로그인한 사용자가 해당 페이지의 회원을 구독하고 있는지 여부를 반환
     */
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :pageUserId", nativeQuery = true)
    int subscribeState(@Param("principalId") int principalId, @Param("pageUserId") int pageUserId);

}
