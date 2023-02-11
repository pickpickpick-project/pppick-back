package com.pickx3.domain.repository.post_package;

import com.pickx3.domain.entity.post_package.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImgRepository extends JpaRepository<PostImg, Long> {
    List<PostImg> findByPost_PostNum(Long postNum);

}