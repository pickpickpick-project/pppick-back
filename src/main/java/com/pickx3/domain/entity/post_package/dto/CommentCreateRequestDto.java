package com.pickx3.domain.entity.post_package.dto;

import com.pickx3.domain.entity.post_package.Comment;
import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.entity.user_package.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreateRequestDto {

    private String commentContent;
    private User user;
    private Post post;
    private String userNum;


    @Builder
    public CommentCreateRequestDto(String userNum, Post post, String commentContent) {
        this.user = user;
        this.post = post;
        this.commentContent = commentContent;
    }

    /* Dto -> Entity */
    public Comment toEntity() {
        Comment comments = Comment.builder()
                .user(user)
                .post(post)
                .commentContent(commentContent)
                .build();

        return comments;
    }


}
