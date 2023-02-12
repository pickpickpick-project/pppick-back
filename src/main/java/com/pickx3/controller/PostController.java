package com.pickx3.controller;

import com.pickx3.domain.dto.post_package.*;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.service.post_package.PostService;
import com.pickx3.service.post_package.PostImgService;
import com.pickx3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public Long create(@ModelAttribute PostFileVO postFileVO) throws Exception {
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

        return postService.create(requestDto, postFileVO.getFiles());
    }


    @PutMapping("/post/{postNum}")
    public Long update(@PathVariable Long postNum, String postPwd, @RequestBody PostUpdateRequestDto requestDto) throws Exception {
        return postService.update(postNum,postPwd,requestDto);
    }

    //개별 조회
    @GetMapping("/post/{postNum}")
    public PostResponseDto searchById(@PathVariable Long postNum) {
        return postService.searchByPostNum(postNum);
    }

    //전체 조회(목록)
    @GetMapping("/post")
    public List<PostListResponseDto> searchAllDesc() {
        return postService.searchAllDesc();
    }

    @DeleteMapping("/post/{postNum}")
    public void delete(@PathVariable Long postNum) {
        postService.delete(postNum);
    }
}