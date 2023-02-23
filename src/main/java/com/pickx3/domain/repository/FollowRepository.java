package com.pickx3.domain.repository;

import com.pickx3.domain.entity.Follow;
import com.pickx3.domain.entity.user_package.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findByFollowerNumAndFollowingNum(User followerNum, User followingNum);




  //  Long countByFollowerNum(User user);    // 팔로워
   // Long countByFAndFollowingNum(User user);  // 팔로우

   // List<Follow> findByUser_id(Long id);

}
