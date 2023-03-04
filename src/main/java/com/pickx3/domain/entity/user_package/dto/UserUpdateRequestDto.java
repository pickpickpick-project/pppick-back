package com.pickx3.domain.entity.user_package.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Data
@Schema(title = "유저 파일 Vo")
public class UserUpdateRequestDto {
    private String userNick;
    private String userPhone;
    private String userIntro;

    @Schema(title = "유저 File Array", description = "유저 File Array")
    private MultipartFile userImg;


}
