package com.pickx3.domain.repository;

import com.pickx3.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findFollowByFollowerNumAndFollowingNum(Long followerNum, Long followingNum);

    List<Follow> findByFollowerNum(Long followerNum);   //팔로우 조회 -> 팔로잉 반환

    List<Follow> findByFollowingNum(Long followingNum);  //팔로잉 조회 -> 발로우 번호 반환

}
