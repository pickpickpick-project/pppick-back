package com.pickx3.domain.repository;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.work_package.Work;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorkRepositoryTest {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void clear(){
        userRepository.deleteAll();
        workRepository.deleteAll();
    }

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

    @Test
    public void 상품목록조회(){
        User user = 회원생성();
        List<Work> works = new ArrayList<>();

//      given
        for(int i = 0; i < 5; i++){
            Work work =  Work.builder()
                    .workName("상품"+i)
                    .workDesc("상품 설명" + i)
                    .workPrice(25000 + i)
                    .userInfo(user)
                    .build();

            workRepository.save(work);
            works.add(work);
        }

//      when
        int size = workRepository.findAll().size();

        Assertions.assertEquals(5, size);
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