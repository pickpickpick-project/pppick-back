//package com.pickx3.domain.repository;
//
//import com.nimbusds.openid.connect.sdk.claims.UserInfo;
//import com.pickx3.domain.entity.user_package.Role;
//import com.pickx3.domain.entity.user_package.User;
//import com.pickx3.domain.entity.work_package.Work;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Transactional
//@Rollback(value = false)
//@SpringBootTest
//class WorkRepositoryTest {
//
//    @Autowired
//    private WorkRepository workRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    public void clear(){
//        userRepository.deleteAll();
//        workRepository.deleteAll();
//    }
//
//    @Test
//    public void 상품등록(){
//        User user = 회원생성();
//        Work work =  Work.builder()
//                .workName("상품1")
//                .workDesc("상품 설명1")
//                .workPrice(25000)
//                .userInfo(user)
//                .build();
//
//        Work result = workRepository.save(work);
//
//        Assertions.assertEquals(1L, result.getWorkNum());
//    }
//
//    @Test
//    public void 상품목록조회(){
//        User user = 회원생성();
//        User user2 = User.builder()
//                .email("test2@naver.com")
//                .nickName("test2")
//                .name("test2")
//                .providerId("naver")
//                .role(Role.USER)
//                .build();
//        userRepository.save(user2);
//
//        List<Work> works = new ArrayList<>();
//
////      given
//        for(int i = 0; i < 5; i++){
//            Work work =  Work.builder()
//                    .workName("상품"+i)
//                    .workDesc("상품 설명" + i)
//                    .workPrice(25000 + i)
//                    .userInfo(user)
//                    .build();
//
//            workRepository.save(work);
//            works.add(work);
//        }
//
//        Work work2 = Work.builder()
//                .workName("상품6")
//                .workDesc("상품 설명6")
//                .workPrice(35000)
//                .userInfo(user2)
//                .build();
//        workRepository.save(work2);
//        works.add(work2);
////      when
//        int size = workRepository.findByUserInfo_id(user.getId()).size();
//
//        Assertions.assertEquals(5, size);
//    }
//
//    @Test
//    public void 상품상세정보조회(){
//        User user = 회원생성();
//        Work work =  Work.builder()
//                .workName("상품1")
//                .workDesc("상품 설명1")
//                .workPrice(25000)
//                .userInfo(user)
//                .build();
//
//        work = workRepository.save(work);
//
//        Work result = workRepository.findById(work.getWorkNum()).get();
//
//        assertThat(result).usingRecursiveComparison().isEqualTo(work);
//    }
//
//    @Test
//    public void 상품정보수정(){
////      given
//        Work work = 상품생성();
//        Long workNum = work.getWorkNum();
//
////      when
//        Work work2= workRepository.findById(workNum).get();
//        work2.updateWork("만화", 55000, "만화 설명");
//
//        assertEquals(work.getWorkName(), work2.getWorkName());
//    }
//
//    @Test
//    public void 상품정보삭제(){
////      given
//        User user = 회원생성();
//        List<Work> works = new ArrayList<>();
//
//        for(int i = 0; i < 5; i++){
//            Work work =  Work.builder()
//                    .workName("상품"+i)
//                    .workDesc("상품 설명" + i)
//                    .workPrice(25000 + i)
//                    .userInfo(user)
//                    .build();
//
//            workRepository.save(work);
//            works.add(work);
//        }
//
////      when
//        workRepository.deleteById(2L);
//
////      then
//        assertEquals(4, workRepository.findAll().size() );
//
//
//    }
//
//    public User 회원생성(){
//        User user = User.builder()
//                .email("test@naver.com")
//                .password("123456")
//                .build();
//        userRepository.save(user);
//        return user;
//
//    }
//
//    public Work 상품생성(){
//        User user = 회원생성();
//        Work work =  Work.builder()
//                .workName("상품1")
//                .workDesc("상품 설명1")
//                .workPrice(25000)
//                .userInfo(user)
//                .build();
//        workRepository.save(work);
//        return work;
//    }
//
//}