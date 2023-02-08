package com.pickx3.domain.dto;

import com.pickx3.domain.entity.post_package.Post;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long postNum;
    private String member;
    private String postTitle;

    public PostListResponseDto(Post entity) {
        this.postNum = entity.getPostNum();
        this.member = entity.getUser().getName();
        this.postTitle = entity.getTitle();
    }
}