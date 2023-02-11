package com.pickx3.domain.repository.post_package;

import com.pickx3.domain.entity.post_package.Comment;
import com.pickx3.domain.entity.post_package.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_PostNum(Long postNum);
}