package com.portfolio.sns.service;

import com.portfolio.sns.dto.subscribe.SubscribeDto;
import com.portfolio.sns.exception.CustomAPIException;
import com.portfolio.sns.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * The type Subscribe service.
 */
@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final EntityManager entityManager;

    /**
     * @param from user id
     * @param to   user id
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
     * @param from user id
     * @param to   user id
     * @methodName : unSubscribe
     * @author : rulethecode9060
     * @date : 2023.07.17
     * @description : 구독취소 요청(Service->Repository)
     */
    @Transactional
    public void unSubscribe(int fromUserId, int toUserId){
        subscribeRepository.unSubscribe(fromUserId, toUserId);
    }

    /**
     * @param principalId
     * @param pageUserId
     * @return list
     * @methodName : subscribeList
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 구독 목록 불러오기(Service->Repository)
     */
    @Transactional(readOnly = true)
    public List<SubscribeDto> subscribeList(int principalId, int pageUserId){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT u.id, u.username, u.profileImageUrl, ");
        stringBuffer.append("if((SELECT true FROM subscribe WHERE fromUserId=? AND toUserId=u.id), 1, 0) subscribeState, ");
        stringBuffer.append("if((?=u.id), 1, 0) equalUserState ");
        stringBuffer.append("FROM user u INNER JOIN subscribe s ");
        stringBuffer.append("ON u.id = s.toUserId ");
        stringBuffer.append("WHERE fromUserId=?");


        Query query = entityManager.createNativeQuery(stringBuffer.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        JpaResultMapper resultMapper = new JpaResultMapper();
        List<SubscribeDto> subScribeList = resultMapper.list(query, SubscribeDto.class);
        return subScribeList;
    }
}
