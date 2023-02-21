package com.pickx3.domain.repository;

import com.pickx3.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFollowerNumAndFollowingNum(Long followerNum, Long followingNum);
  //  Long countByFollowerNum(User user);    // 팔로워
   // Long countByFAndFollowingNum(User user);  // 팔로우

    List<Follow> findByUser_id(Long id);

}
