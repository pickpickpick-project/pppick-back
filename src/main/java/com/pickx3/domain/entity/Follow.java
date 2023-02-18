package com.pickx3.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followNum;

    private Long followerNum;
    private Long followingNum;


    @Builder
    public Follow(Long followNum, Long followerNum, Long followingNum) {
        this.followNum = followNum;
        this.followerNum = followerNum;
        this.followingNum = followingNum;
    }
}
