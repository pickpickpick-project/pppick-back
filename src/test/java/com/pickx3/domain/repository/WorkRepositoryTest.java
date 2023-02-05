package com.pickx3.domain.repository;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.work_package.Work;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorkRepositoryTest {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 상품등록(){
        User user = 회원생성();
        Work work =  Work.builder()
                .workName("상품1")
                .workDesc("상품 설명1")
                .workPrice(25000)
                .userInfo(user)
                .build();

        Work result = workRepository.save(work);

        Assertions.assertEquals(1L, result.getWorkNum());
    }

    public User 회원생성(){
        User user = User.builder()
                .email("test@naver.com")
                .password("123456")
                .build();
        userRepository.save(user);
        return user;

    }

}