/*
package com.pickx3.domain.post;

import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.post_package.PostRepository;
import com.pickx3.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


    @Test
    public void postCallBack() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String pwd = "1234";
        User user = User.builder().email("kioup123").name("김소윤").build();
        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .pwd(pwd)
                .build());

        //when
        List<Post> postsList = postRepository.findAll();

        //then
        Post post = postsList.get(0);
        System.out.println(post.toString());
    }


}*/
