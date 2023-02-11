package com.pickx3.controller;

import com.pickx3.domain.dto.post_package.CommentCreateRequestDto;
import com.pickx3.domain.dto.post_package.CommentResponseDto;
import com.pickx3.domain.dto.post_package.CommentSetVO;
import com.pickx3.domain.dto.post_package.CommentUpdateRequestDto;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.post_package.CommentRepository;
import com.pickx3.service.UserService;
import com.pickx3.service.post_package.CommentService;
import com.pickx3.util.rsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> commentSave(@PathVariable Long postNum, @RequestBody CommentSetVO commentSetVO) {
        User user = userService.searchUserById(
                Long.parseLong(commentSetVO.getUserNum()));
        CommentCreateRequestDto dto = CommentCreateRequestDto.builder()
                .user(user)
                .commentContent(commentSetVO.getCommentContent())
                .build();

        rsMessage result;
        try{
            long commentNum= commentService.commentSave(dto, postNum);
            CommentResponseDto commentResponseDto = commentService.searchById(commentNum);
            result = new rsMessage(true, "Success" ,"200", "", commentResponseDto );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }

    /* READ */
    // comment 번호로 조회
    @GetMapping("/comment/{commentNum}")
    public ResponseEntity<?> searchById(@PathVariable Long commentNum) {
        rsMessage result;
        try{
            CommentResponseDto commentResponseDto = commentService.searchById(commentNum);
            result = new rsMessage(true, "Success" ,"200", "", commentResponseDto );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("post/{postNum}/comment")
    public ResponseEntity<?> searchByPost(@PathVariable Long postNum) {
        rsMessage result;
        try{
            List<CommentResponseDto> commentResponseDtos = commentService.searchByPost(postNum);
            result = new rsMessage(true, "Success" ,"200", "", commentResponseDtos );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    // 전체 조회
    @GetMapping("/comment")
    public ResponseEntity<?> searchAll() {
        rsMessage result;
        try{
            List<CommentResponseDto> commentResponseDtos = commentService.searchAll();
            result = new rsMessage(true, "Success" ,"200", "", commentResponseDtos );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }


    /* UPDATE */
    @PutMapping("/comment/{commentNum}")
    public ResponseEntity<?> update(@PathVariable Long commentNum, @RequestBody CommentUpdateRequestDto requestDto) {
        rsMessage result;
        try{
            long newCommentNum= commentService.commentUpdate(commentNum, requestDto);
            CommentResponseDto commentResponseDto = commentService.searchById(newCommentNum);
            result = new rsMessage(true, "Success" ,"200", "", commentResponseDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    /* DELETE */
    @DeleteMapping("/comment/{commentNum}")
    public ResponseEntity<?> delete(@PathVariable Long commentNum) {
        rsMessage result;
        try{
            commentService.delete(commentNum);
            result = new rsMessage(true, "Success" ,"200", "", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
