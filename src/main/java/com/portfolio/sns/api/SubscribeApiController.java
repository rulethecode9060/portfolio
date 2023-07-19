package com.portfolio.sns.api;

import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.dto.common.CommonResponseDto;
import com.portfolio.sns.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscribeApiController {
    private final SubscribeService subscribeService;

    /**
     * @param principalDetails
     * @param toUserId
     * @return responseEntity
     * @methodName : subscribe
     * @author : rulethecode9060
     * @date : 2023.07.16
     * @description : 구독하기 요청
     */
    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
        subscribeService.subscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "구독 하기 성공", null), HttpStatus.OK);
    }

    /**
     * @param principalDetails
     * @param toUserId
     * @return responseEntity
     * @methodName : unSubscribe
     * @author : rulethecode9060
     * @date : 2023.07.16
     * @description : 구독취소 요청
     */
    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
        subscribeService.unSubscribe(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "구독 취소 성공", null), HttpStatus.OK);
    }
}
