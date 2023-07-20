package com.portfolio.sns.api;

import com.portfolio.sns.domain.Image;
import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.dto.common.CommonResponseDto;
import com.portfolio.sns.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Image api controller.
 */
@RestController
@RequiredArgsConstructor
public class ImageApiController {
    private final ImageService imageService;

    /**
     * @param principalDetails
     * @param pageable
     * @return response entity
     * @methodName : story
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 스토리(메인) 페이지 이미지 조회 API
     */
    @GetMapping("/api/image")
    public ResponseEntity<?> story(@AuthenticationPrincipal PrincipalDetails principalDetails
    , @PageableDefault(size = 3) Pageable pageable){
        Page<Image> images = imageService.story(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CommonResponseDto<>(1, "스토리 이미지 불러오기 성공", images), HttpStatus.OK);
    }

}
