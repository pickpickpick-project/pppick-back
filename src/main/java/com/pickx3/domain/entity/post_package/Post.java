package com.pickx3.domain.entity.post_package;

import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNum;

    @ManyToOne
    @JoinColumn(name = "userNum")
    private User user;

    @Column(name ="postTitle")
    private String title;

    @Column(name="postContent")
    private String content;

    @Column(name="postPwd")
    private String pwd;

    @Column(name = "postDate", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;

    @OneToMany(
            mappedBy = "post",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<PostImg> postImg = new ArrayList<>();

    @Builder
    public Post(User user, String title, String content, String pwd) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.pwd = pwd;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Board에서 파일 처리 위함
    public void addPostImg(PostImg postImg) {
        this.postImg.add(postImg);

        // 게시글에 파일이 저장되어있지 않은 경우
        if(postImg.getPost() != this){
            // 파일 저장
            postImg.setPost(this);

        }
    }
}
