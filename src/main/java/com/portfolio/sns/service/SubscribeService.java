package com.portfolio.sns.service;

import com.portfolio.sns.exception.CustomAPIException;
import com.portfolio.sns.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Subscribe service.
 */
@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;

    /**
     * @param fromUserId
     * @param toUserId
     * @methodName : subscribe
     * @author : rulethecode9060
     * @date : 2023.07.17
     * @description : 구독하기 요청(Service->Repository)
     */
    @Transactional
    public void subscribe(int fromUserId, int toUserId){
        try {
            subscribeRepository.subscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomAPIException("이미 구독 되어있는 회원입니다.");
        }
    }

    /**
     * @param fromUserId
     * @param toUserId
     * @methodName : unSubscribe
     * @author : rulethecode9060
     * @date : 2023.07.17
     * @description : 구독취소 요청(Service->Repository)
     */
    @Transactional
    public void unSubscribe(int fromUserId, int toUserId){
        subscribeRepository.unSubscribe(fromUserId, toUserId);
    }
}
