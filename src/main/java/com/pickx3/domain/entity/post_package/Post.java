package com.pickx3.domain.entity.post_package;

import com.pickx3.domain.entity.user_package.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNum;
    private String postTitle;
    private String postContent;
    private LocalDateTime postDate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userNum")
    private User userInfo;

}
