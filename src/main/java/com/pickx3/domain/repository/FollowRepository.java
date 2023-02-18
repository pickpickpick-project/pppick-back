package com.pickx3.domain.repository;

import com.pickx3.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFollowerNumAndFollowingNum(Long followerNum, Long followingNum);
}
