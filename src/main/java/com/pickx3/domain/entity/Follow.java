package com.pickx3.domain.entity;

import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followNum;

    @JoinColumn(name = "followerNum")
    @ManyToOne(fetch = FetchType.LAZY)
    private User followerNum;

    @JoinColumn(name = "followingNum")
    @ManyToOne(fetch = FetchType.LAZY)
    private User followingNum;


    @Builder
    public Follow(Long followNum, User followerNum, User followingNum) {
        this.followNum = followNum;
        this.followerNum = followerNum;
        this.followingNum = followingNum;
    }
}
