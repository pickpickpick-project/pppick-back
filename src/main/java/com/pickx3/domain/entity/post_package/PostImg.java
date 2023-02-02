package com.pickx3.domain.entity.post_package;

import javax.persistence.*;

@Entity
public class PostImg {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postImgNum;
    private Long postNum;
    private String postImgOriginName;
    private String postImgName;
    private String postImgSrcPath;

    @ManyToOne
    private Post post;
}
