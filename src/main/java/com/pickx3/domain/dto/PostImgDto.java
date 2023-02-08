package com.pickx3.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostImgDto {
    private String postImgOriginName;
    private String postImgSrcPath;
    private Long fileSize;

    @Builder
    public PostImgDto(String origFileName, String filePath, Long fileSize){
        this.postImgOriginName = origFileName;
        this.postImgSrcPath = filePath;
        this.fileSize = fileSize;
    }
}