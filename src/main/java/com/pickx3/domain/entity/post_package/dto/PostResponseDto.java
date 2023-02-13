package com.pickx3.domain.entity.post_package.dto;

import com.pickx3.domain.entity.post_package.Comment;
import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.entity.post_package.PostImg;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

//@Getter
@Data
public class PostResponseDto {
    private Long postNum;

    private Long userNum;
    private String userName;
    private String postTitle;
    private String postContent;

    private String postPwd;
    private List<CommentResponseDto> comments;

    private List<PostImg> postImgs;
    public PostResponseDto(Post entity) {
        this.postNum = entity.getPostNum();
        this.userNum = entity.getUser().getId();
        this.userName = entity.getUser().getName();
        this.postTitle = entity.getTitle();
        this.postContent = entity.getContent();
        this.postPwd = entity.getPwd();
        this.comments = entity.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.postImgs = entity.getPostImg();
    }
}