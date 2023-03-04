package com.pickx3.domain.entity.post_package.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostImgDto {
    private String postImgOriginName;
    private String postImgSrcPath;
    private String postImgName;
    private Long postImgSize;

    @Builder
    public PostImgDto(String origFileName, String filePath, Long postImgSize, String postImgName){
        this.postImgOriginName = origFileName;
        this.postImgName = postImgName;
        this.postImgSrcPath = filePath;
        this.postImgSize = postImgSize;
    }
}