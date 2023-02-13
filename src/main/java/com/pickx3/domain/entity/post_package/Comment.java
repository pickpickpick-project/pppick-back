package com.pickx3.domain.entity.post_package;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pickx3.domain.entity.user_package.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Comment {
    @Id
    @Column(name="commentNum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNum;

    @Column(name = "commentContent")
    private String commentContent;


    @Column(name = "commentDate")
    private String commentDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userNum")
    private User userInfo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "postNum")
    private Post post;

    @Builder
    public Comment(User user, Post post, String commentContent, String commentDate) {
        this.userInfo = user;
        this.post = post;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }


    public void update(String content) {
        this.commentContent = content;
    }
}
