package com.portfolio.sns.dto.image;

import com.portfolio.sns.domain.Image;
import com.portfolio.sns.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

/**
 * The type Image upload dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageUploadDto {

    /**
     * 파일
     */
    private MultipartFile file;
    /**
     * 사진 설명
     */
    @NotBlank
    private String caption;

    /**
     * @param postImageUrl
     * @param user
     * @return image
     * @methodName : toEntity
     * @author : rulethecode9060
     * @date : 2023.07.14
     * @description : DTO -> Entity로 변경
     */
    public Image toEntity(String postImageUrl, User user){
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }


}
