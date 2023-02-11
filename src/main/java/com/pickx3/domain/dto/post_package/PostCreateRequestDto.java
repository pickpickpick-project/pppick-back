package com.pickx3.domain.dto.post_package;

import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequestDto {
    private User user;
    private String postTitle;
    private String postContent;

    private String postPwd;

    @Builder
    public PostCreateRequestDto(User user, String postTitle, String postContent, String postPwd) {
        this.user = user;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postPwd = postPwd;
    }

    public Post toEntity() {
        return Post.builder()
                .user(user)
                .title(postTitle)
                .content(postContent)
                .pwd(postPwd)
                .build();
    }
}