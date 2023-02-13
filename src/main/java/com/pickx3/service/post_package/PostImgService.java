package com.pickx3.service.post_package;

import com.pickx3.domain.dto.post_package.PostFileVO;
import com.pickx3.domain.dto.post_package.PostImgDto;
import com.pickx3.domain.dto.post_package.PostImgResponseDto;
import com.pickx3.domain.entity.post_package.PostImg;
import com.pickx3.domain.entity.work_package.WorkImg;
import com.pickx3.domain.entity.work_package.dto.work.WorkImgForm;
import com.pickx3.domain.repository.post_package.PostImgRepository;
import com.pickx3.domain.repository.post_package.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostImgService {
    private final PostImgRepository postImgRepository;
    private final PostRepository postRepository;
    /**
     * 이미지 개별 조회
     */
    @Transactional(readOnly = true)
    public PostImgDto findByFileId(Long id){

        PostImg entity = postImgRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 파일이 존재하지 않습니다."));

        PostImgDto postImgDto = PostImgDto.builder()
                .origFileName(entity.getOrigFileName())
                .filePath(entity.getFilePath())
                .postImgSize(entity.getPostImgSize())
                .build();

        return postImgDto;
    }

    /**
     * 이미지 전체 조회
     */
    @Transactional(readOnly = true)
    public List<PostImgResponseDto> findAllByPost(Long postNum){

        List<PostImg> postImgList = postImgRepository.findByPost_PostNum(postNum);

        return postImgList.stream()
                .map(PostImgResponseDto::new)
                .collect(Collectors.toList());
    }


    
}