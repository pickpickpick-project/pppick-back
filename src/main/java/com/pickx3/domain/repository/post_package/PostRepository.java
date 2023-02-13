package com.pickx3.domain.repository.post_package;

import com.pickx3.domain.entity.post_package.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

   List<Post> findByPostBoardNum(Long postBoardNum);

   List<Post> findByUser_Id(Long userNum);
}