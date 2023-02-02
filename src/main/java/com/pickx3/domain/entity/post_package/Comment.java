package com.pickx3.domain.entity.post_package;

import com.pickx3.domain.entity.user_package.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNum;

    private String commentContent;

    private LocalDateTime commentDate;

    @ManyToOne
    @JoinColumn(name = "userNum")
    private User userInfo;

    @ManyToOne
    @JoinColumn(name = "postNum")
    private Post post;
}
