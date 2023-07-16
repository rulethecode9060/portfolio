package com.portfolio.sns.repository;

import com.portfolio.sns.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
    void subscribe(int fromUserId, int toUserId);

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
    void unSubscribe(int fromUserId, int toUserId);

}
