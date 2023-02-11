package com.pickx3.domain.dto.post_package;

import com.pickx3.domain.entity.post_package.Post;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long postNum;
    private String userName;
    private String postTitle;

    public PostListResponseDto(Post entity) {
        this.postNum = entity.getPostNum();
        this.userName = entity.getUser().getName();
        this.postTitle = entity.getTitle();
    }
}