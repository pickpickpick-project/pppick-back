//package com.pickx3.domain.repository;
//
//import com.pickx3.domain.entity.user_package.User;
//import com.pickx3.domain.entity.work_package.Work;
//import com.pickx3.domain.entity.work_package.WorkImg;
//import com.pickx3.domain.entity.work_package.dto.work.WorkImgForm;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//@Rollback(value = false)
//class WorkImgRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private WorkRepository workRepository;
//    @Autowired
//    private WorkImgRepository workImgRepository;
//
//    @Test
//    public void 상품이미지목록조회(){
//
//        List<WorkImg> workImgs = 상품이미지목록생성();
//
//        List<WorkImgForm> result = workImgRepository.findByWork_workNum(7L);
//
//        System.out.println("상품정보 "+result.get(0).getWorkImgName());
//
//        assertEquals(5, result.size() );
//    }
//
//    public User 회원생성(){
//        User user = User.builder()
//                .email("test@naver.com")
//                .name("test")
//                .nickName("test")
//                .build();
//
//        userRepository.save(user);
//        return user;
//    }
//
//    public Work 상품생성(){
//        User user = 회원생성();
//        Work work = Work.builder()
//                .workName("상품1")
//                .workDesc("상품설명1")
//                .workPrice(30000)
//                .userInfo(user)
//                .build();
//        workRepository.save(work);
//        return work;
//    }
//
//    public List<WorkImg> 상품이미지목록생성(){
//        Work work = 상품생성();
//
//        List<WorkImg> workImgs = new ArrayList<>();
//
//        for(int i = 0; i < 5; i++){
//            WorkImg workImg = WorkImg.builder()
//                    .workImgName("20230211-test" + i)
//                    .workImgOriginName("test" + i)
//                    .workImgSrcPath("/dev/resource/img" + i)
//                    .work(work)
//                    .build();
//
//            workImgRepository.save(workImg);
//            workImgs.add(workImg);
//        }
//
//        return workImgs;
//
//    }
//}