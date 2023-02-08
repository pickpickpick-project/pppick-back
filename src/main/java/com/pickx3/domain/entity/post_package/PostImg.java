package com.pickx3.domain.entity.post_package;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "postImg")
public class PostImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postImgNum")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postNum")
    private Post post;

    @Column(name = "postImgOriginName")
    private String origFileName;  // 파일 원본명


    @Column(name = "postImgSrcPath")
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    @Builder
    public PostImg(String origFileName, String filePath, Long fileSize){
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // Board 정보 저장
    public void setPost(Post post){
        this.post = post;

        // 게시글에 현재 파일이 존재하지 않는다면
        if(!post.getPostImg().contains(this)){
            // 파일 추가
            post.getPostImg().add(this);

        }
    }
}
