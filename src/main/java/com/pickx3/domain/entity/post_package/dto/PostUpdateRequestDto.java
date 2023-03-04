package com.pickx3.domain.entity.post_package.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Data
@NoArgsConstructor
@Schema(title = "파일 Vo")
public class PostUpdateRequestDto {
    private String postTitle;
    private String postContent;

    @Schema(title = "멀티파트 File Array", description = "멀티 파트 File Array")
    private List<MultipartFile> files;
    @Builder
    public PostUpdateRequestDto(String title, String postContent) {
        this.postTitle = title;
        this.postContent = postContent;
    }
}