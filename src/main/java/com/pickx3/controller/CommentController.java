package com.pickx3.controller;

import com.pickx3.domain.entity.post_package.dto.CommentCreateRequestDto;
import com.pickx3.domain.entity.post_package.dto.CommentResponseDto;
import com.pickx3.domain.entity.post_package.dto.CommentSetVO;
import com.pickx3.domain.entity.post_package.dto.CommentUpdateRequestDto;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.post_package.CommentRepository;
import com.pickx3.service.UserService;
import com.pickx3.service.post_package.CommentService;
import com.pickx3.util.rsMessage;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "댓글 저장", description = "test example:<br>postNum - 1<br>commentContent - 네 인물 작업 가능합니다<br>userNum - 3")
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
    @Operation(summary = "댓글 상세 정보 조회", description = "test example :<br>commentNum - 1")
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

    @Operation(summary = "게시물별 댓글 목록 조회", description = "test example:<br>postNum - 1")
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
    @Operation(summary = "댓글 전체 조회")
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
    @Operation(summary = "댓글 수정", description = "test example:<br>commentNum - 1<br>commentContent - 네, 캐릭터 작업 가능합니다")
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
    @Operation(summary = "댓글 삭제", description = "delete api 이용할 경우 post api로 등록하여 결과 값으로 나온 고유번호로 이용 부탁드립니다.<br>" +
            "고유번호 ex) postNum , portfolioNum, WorkNum, commentNum")
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
