package com.pickx3.domain.entity;

import lombok.Getter;

@Getter
public class FollowResponse {

    private Long followerNum;

    private Long followingNum;




    // Entitiy -> DTO
    public FollowResponse(Follow follow) {

        this.followerNum = followerNum;
        this.followingNum = followingNum;
    }
}
