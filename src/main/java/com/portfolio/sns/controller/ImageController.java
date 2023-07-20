package com.portfolio.sns.controller;

import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.dto.image.ImageUploadDto;
import com.portfolio.sns.exception.CustomValidationException;
import com.portfolio.sns.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Image controller.
 */
@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    /**
     * @return string
     * @methodName : upload
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 업로드 페이지로 이동
     */
    @GetMapping({"image/upload"})
    public String upload(){
        return "image/upload";
    }

    /**
     * @return string
     * @methodName : story
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 스토리(메인) 페이지로 이동
     */
    @GetMapping({"/", "image/story"})
    public String story(){
        return "image/story";
    }

    /**
     * @param imageUploadDto
     * @param bindingResult
     * @param principalDetails
     * @return string
     * @methodName : imageUpload
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 이미지 업로드 요청
     */
    @PostMapping("/image")
    public String imageUpload(@Valid ImageUploadDto imageUploadDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("이미지 업로드 실패", errorMap);
        }
        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지 파일이 첨부되지 않았습니다.", null);
        }

        imageService.imageUpload(imageUploadDto, principalDetails);
        return "redirect:/user/"+principalDetails.getUser().getId();
    }
}
