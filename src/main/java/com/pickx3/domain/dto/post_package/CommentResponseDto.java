package com.pickx3.domain.dto.post_package;

import com.pickx3.domain.entity.post_package.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponseDto {


    private Long commentNum;
    private String commentContent;
    private Long userNum;
    private String userNick;


    private Long postNum;

    /* Entity -> Dto*/
    public CommentResponseDto(Comment comment) {
        this.commentNum = comment.getCommentNum();
        this.commentContent = comment.getCommentContent();
        this.userNum = comment.getUserInfo().getId();
        this.userNick = comment.getUserInfo().getNickName();
        this.postNum = comment.getPost().getPostNum();

    }
}