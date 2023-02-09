package com.pickx3.domain.dto;

import com.pickx3.domain.entity.post_package.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long postNum;

    private Long userNum;
    private String userName;
    private String postTitle;
    private String postContent;

    private String postPwd;
    public PostResponseDto(Post entity) {
        this.postNum = entity.getPostNum();
        this.userNum = entity.getUser().getId();
        this.userName = entity.getUser().getName();
        this.postTitle = entity.getTitle();
        this.postContent = entity.getContent();
        this.postPwd = entity.getPwd();
    }
}