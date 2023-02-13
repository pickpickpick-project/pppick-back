package com.pickx3.domain.entity.post_package.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Data
@Schema(title = "파일 Vo")
public class PostFileVO {
    private String userNum;
    private String postTitle;
    private String postContent;
    private String postPwd;

    @Schema(title = "멀티파트 File Array", description = "멀티 파트 File Array")
    private List<MultipartFile> files;
}