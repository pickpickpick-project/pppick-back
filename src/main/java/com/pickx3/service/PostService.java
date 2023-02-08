package com.pickx3.service;

import com.pickx3.domain.dto.PostCreateRequestDto;
import com.pickx3.domain.dto.PostListResponseDto;
import com.pickx3.domain.dto.PostResponseDto;
import com.pickx3.domain.dto.PostUpdateRequestDto;
import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.entity.post_package.PostImg;
import com.pickx3.domain.repository.PostRepository;
import com.pickx3.domain.repository.PostImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostImgRepository postImgRepository;
    private final FileHandler fileHandler;


    @Transactional
    public Long create(
            PostCreateRequestDto requestDto,
            List<MultipartFile> files
    ) throws Exception {
        // 파일 처리를 위한 Board 객체 생성
        Post post = new Post(
                requestDto.getUser(),
                requestDto.getPostTitle(),
                requestDto.getPostContent(),
                requestDto.getPostPwd()
        );

        List<PostImg> postImgList = fileHandler.parseFileInfo(files);

        // 파일이 존재할 때에만 처리
        if(!postImgList.isEmpty()) {
            for(PostImg postImg : postImgList) {
                // 파일을 DB에 저장
                post.addPostImg(postImgRepository.save(postImg));
            }
        }

        return postRepository.save(post).getPostNum();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.update(requestDto.getPostTitle(),
                requestDto.getPostContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostResponseDto searchById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> searchAllDesc() {
        return postRepository.findAll().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        postRepository.delete(post);
    }

}