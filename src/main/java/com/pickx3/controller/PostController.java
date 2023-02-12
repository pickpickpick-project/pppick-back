package com.pickx3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pickx3.domain.dto.post_package.*;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.service.post_package.PostService;
import com.pickx3.service.post_package.PostImgService;
import com.pickx3.service.UserService;
import com.pickx3.util.rsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostImgService postImgService;
    private final UserService userService;

    @PostMapping(path = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@ModelAttribute PostFileVO postFileVO) throws Exception {
        // Member id로 조회하는 메소드 존재한다고 가정하에 진행
        User user = userService.searchUserById(
                Long.parseLong(postFileVO.getUserNum()));

        PostCreateRequestDto requestDto =
                PostCreateRequestDto.builder()
                        .user(user)
                        .postTitle(postFileVO.getPostTitle())
                        .postContent(postFileVO.getPostContent())
                        .postPwd(postFileVO.getPostPwd())
                        .build();
        rsMessage result;

        try{
            long postNum= postService.create(requestDto, postFileVO.getFiles());
            PostResponseDto postResponseDto = postService.searchByPostNum(postNum);
            result = new rsMessage(true, "Success" ,"200", "", postResponseDto );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/post/{postNum}")
    public ResponseEntity<?> update(@PathVariable Long postNum, String postPwd, @RequestBody PostUpdateRequestDto requestDto) throws Exception {
        rsMessage result;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Long newPostNum = postService.update(postNum,postPwd,requestDto);
            PostResponseDto postResponseDto = postService.searchByPostNum(newPostNum);
            result = new rsMessage(true, "Success" ,"200", "", postResponseDto );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    //개별 조회
    @GetMapping("/post/{postNum}")
    public ResponseEntity<?> searchById(@PathVariable Long postNum) {
        rsMessage result;
        try{
            PostResponseDto postResponseDto =postService.searchByPostNum(postNum);
            result = new rsMessage(true, "Success" ,"200", "", postResponseDto );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    //전체 조회(목록)
    @GetMapping("/post")
    public ResponseEntity<?> searchAllDesc() {
        rsMessage result;
        try{
            List<PostListResponseDto> postListResponseDtos =  postService.searchAllDesc();
            result = new rsMessage(true, "Success" ,"200", "", postListResponseDtos );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/post/{postNum}")
    public ResponseEntity<?> delete(@PathVariable Long postNum) {
        rsMessage result;
        try{
            postService.delete(postNum);
            result = new rsMessage(true, "Success" ,"200", "", null );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}