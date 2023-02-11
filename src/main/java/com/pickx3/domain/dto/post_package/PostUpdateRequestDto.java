package com.pickx3.domain.dto.post_package;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String postTitle;
    private String postContent;

    @Builder
    public PostUpdateRequestDto(String title, String postContent) {
        this.postTitle = title;
        this.postContent = postContent;
    }
}