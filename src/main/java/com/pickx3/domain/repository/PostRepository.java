package com.pickx3.domain.repository;

import com.pickx3.domain.entity.post_package.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

}