package com.pickx3.domain.entity.post_package.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {

    private String commentContent;

    @Builder
    public CommentUpdateRequestDto(String commentContent) {
        this.commentContent = commentContent;
    }
}