package com.pickx3.domain.dto.post_package;

import com.pickx3.domain.entity.post_package.PostImg;
import lombok.Getter;

@Getter
public class PostImgResponseDto {
    private Long fileId;  // 파일 id

    public PostImgResponseDto(PostImg entity){
        this.fileId = entity.getId();
    }
}