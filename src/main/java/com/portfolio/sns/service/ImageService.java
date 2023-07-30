package com.portfolio.sns.service;

import com.portfolio.sns.domain.Image;
import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.dto.image.ImageUploadDto;
import com.portfolio.sns.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * The type Image service.
 */
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    /**
     * @param imageUploadDto
     * @param principalDetails
     * @methodName : imageUpload
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : 이미지 업로드 정보 등록(Service->Repository)
     */
    @Transactional
    public void imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String iamgeFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder + iamgeFileName);
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image imageEntity = imageUploadDto.toEntity(iamgeFileName, principalDetails.getUser());
        imageRepository.save(imageEntity);
    }

    /**
     * @param principalId
     * @param pageable
     * @return page
     * @methodName : story
     * @author : rulethecode9060
     * @date : 2023.07.20
     * @description : 스토리(메인) 페이지 이미지 조회(Service->Repository)
     */
    @Transactional(readOnly = true)
    public Page<Image> story(int principalId, Pageable pageable){
        Page<Image> images = imageRepository.story(principalId, pageable);
        images.forEach((image)->{
            image.setLikeCount(image.getLikes().size());
            image.getLikes().forEach((like)->{
                if(like.getUser().getId()==principalId){
                    image.setLikeState(true);
                }
            });
        });
        return images;
    }

    /**
     * @return list
     * @methodName : popular
     * @author : rulethecode9060
     * @date : 2023.07.30
     * @description : 인기 이미지 조회(Service->Repository)
     */
    @Transactional(readOnly = true)
    public List<Image> popular(){
        List<Image> images = imageRepository.popular();
        return images;
    }
}
