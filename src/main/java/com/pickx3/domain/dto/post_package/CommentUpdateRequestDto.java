package com.pickx3.domain.dto.post_package;

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