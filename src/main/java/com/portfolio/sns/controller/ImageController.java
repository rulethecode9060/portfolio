package com.portfolio.sns.controller;

import com.portfolio.sns.domain.Image;
import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.dto.image.ImageUploadDto;
import com.portfolio.sns.exception.CustomValidationException;
import com.portfolio.sns.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
     * @param image     upload dto
     * @param binding   result
     * @param principal details
     * @return string
     * @methodName : imageUpload
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 이미지 업로드 요청
     */
    @PostMapping("/image")
    public String imageUpload(@Valid ImageUploadDto imageUploadDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지 파일이 첨부되지 않았습니다.", null);
        }

        imageService.imageUpload(imageUploadDto, principalDetails);
        return "redirect:/user/"+principalDetails.getUser().getId();
    }

    /**
     * @param model
     * @return string
     * @methodName : popular
     * @author : rulethecode9060
     * @date : 2023.07.30
     * @description : 인기 페이지로 이동
     */
    @GetMapping("/image/popular")
    public String popular(Model model){
        List<Image> images = imageService.popular();
        model.addAttribute("images", images);
        return "image/popular";
    }
}
