package com.pickx3.domain.entity.post_package.dto;

import com.pickx3.domain.entity.post_package.PostImg;
import lombok.Getter;

@Getter
public class PostImgResponseDto {
    private Long fileId;  // 파일 id

    public PostImgResponseDto(PostImg entity){
        this.fileId = entity.getPostImgNum();
    }
}