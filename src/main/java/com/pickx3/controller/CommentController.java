package com.pickx3.controller;

import com.mysql.cj.xdevapi.Schema;
import com.pickx3.domain.dto.post_package.*;
import com.pickx3.domain.entity.post_package.Comment;
import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.post_package.CommentRepository;
import com.pickx3.domain.repository.post_package.PostRepository;
import com.pickx3.service.UserService;
import com.pickx3.service.post_package.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final UserService userService;

    /* CREATE */
    @PostMapping("/post/{postNum}/comment")
    public ResponseEntity commentSave(@PathVariable Long postNum, @RequestBody CommentSetVO commentSetVO) {
        User user = userService.searchUserById(
                Long.parseLong(commentSetVO.getUserNum()));
        CommentCreateRequestDto dto = CommentCreateRequestDto.builder()
                .user(user)
                .commentContent(commentSetVO.getCommentContent())
                .build();

        return ResponseEntity.ok(commentService.commentSave(dto, postNum));
    }

    /* READ */
    // comment 번호로 조회
    @GetMapping("/comment/{commentNum}")
    public CommentResponseDto searchById(@PathVariable Long commentNum) {
        return commentService.searchById(commentNum);
    }

    @GetMapping("post/{postNum}/comment")
    public List<CommentResponseDto> searchByPost(@PathVariable Long postNum) {
        return commentService.searchByPost(postNum);
    }
    // 전체 조회
    @GetMapping("/comment")
    public List<CommentResponseDto> searchAll() {
        return commentService.searchAll();
    }


    /* UPDATE */
    @PutMapping("/comment/{commentNum}")
    public Long update(@PathVariable Long commentNum, @RequestBody CommentUpdateRequestDto requestDto) {
        return commentService.commentUpdate(commentNum, requestDto);
    }

    /* DELETE */
    @DeleteMapping("/comment/{commentNum}")
    public void delete(@PathVariable Long commentNum) {
        commentService.delete(commentNum);
    }
}
