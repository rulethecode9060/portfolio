package com.portfolio.sns.service;

import com.portfolio.sns.domain.Image;
import com.portfolio.sns.dto.auth.PrincipalDetails;
import com.portfolio.sns.dto.image.ImageUploadDto;
import com.portfolio.sns.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

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
}
