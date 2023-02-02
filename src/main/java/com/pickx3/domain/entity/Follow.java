package com.pickx3.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Follow {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followNum;

    private Long followerNum;
    private Long followingNum;
}
