package com.pickx3.service.post_package;

import com.pickx3.domain.entity.post_package.dto.PostCreateRequestDto;
import com.pickx3.domain.entity.post_package.dto.PostListResponseDto;
import com.pickx3.domain.entity.post_package.dto.PostResponseDto;
import com.pickx3.domain.entity.post_package.dto.PostUpdateRequestDto;
import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.entity.post_package.PostImg;
import com.pickx3.domain.repository.post_package.PostRepository;
import com.pickx3.domain.repository.post_package.PostImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String postDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        // 파일 처리를 위한 Board 객체 생성
        Post post = new Post(
                requestDto.getUser(),
                requestDto.getPostBoardNum(),
                requestDto.getPostTitle(),
                requestDto.getPostContent(),
                requestDto.getPostPwd(),
                postDate

        );

        Post post1 = postRepository.save(post);

        List<PostImg> postImgList = fileHandler.parseFileInfo(files);


        // 파일이 존재할 때에만 처리 - list size 0이면 저장 x
        if (postImgList.size()!=0) {
            for (PostImg postImg : postImgList) {
                // 파일을 DB에 저장
                postImg.setPost(post1);
                post1.addPostImg(postImgRepository.save(postImg));
            }
        }


        return post1.getPostNum();
    }

    @Transactional
    public Long update(Long id, String postPwd, PostUpdateRequestDto requestDto) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        if (!post.getPwd().equals(postPwd)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        post.update(requestDto.getPostTitle(),
                requestDto.getPostContent());

        return id;

    }

    @Transactional(readOnly = true)
    public PostResponseDto searchByPostNum(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto searchByPostNumPwd(Long id, String postPwd) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
        if (!post.getPwd().equals(postPwd)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        return new PostResponseDto(post);
    }


    @Transactional(readOnly = true)
    public List<PostListResponseDto> searchAllDesc() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "postNum")).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> searchByPostBoardNum(Long postBoardNum) {
        return postRepository.findByPostBoardNum(postBoardNum).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long postNum) throws IOException {
        Post post = postRepository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        // post에 할당된 postimg 있을 경우 post img도 같이 삭제
        List<PostImg> postImgs = postImgRepository.findByPost_PostNum(postNum);
        if(postImgs.size()!=0){
            for(PostImg postImg : postImgs){
                Path filePath = Paths.get(postImg.getFilePath());
                Files.delete(filePath);
            }

        }
        postRepository.delete(post);

    }

}