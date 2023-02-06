package com.pickx3.service;

import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.dto.WorkForm;
import com.pickx3.domain.entity.work_package.dto.WorkUpdateForm;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.WorkRepository;
import net.bytebuddy.asm.MemberRemoval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class WorkService {
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private UserRepository userRepository;

    /*
    * 상품 등록
    * */
    public Work createWork(WorkForm workForm){
        User user = userRepository.findById(workForm.getWorkerNum()).get();

        Work work = Work.builder()
                .workName(workForm.getWorkName())
                .workDesc(workForm.getWorkDesc())
                .workPrice(workForm.getWorkPrice())
                .userInfo(user)
                .build();

        workRepository.save(work);
        return work;
    }

    /*
     * 회원별 상품 목록 조회
     * */
    public List<Work> getWorks(Long workerNum){
        return workRepository.findByUserInfo_id(workerNum);
    }
    
    /*
     * 상품 상세정보 조회
     * */
    public Work getWorkInfo(Long workNum){
        return workRepository.findById(workNum).get();
    }

    /*
     * 상품 정보 수정
     * */
    public Work updateWork(WorkUpdateForm workUpdateForm){
        Long workNum = workUpdateForm.getWorkNum();
        String workName = workUpdateForm.getWorkName();
        int workPrice = workUpdateForm.getWorkPrice();
        String workDesc = workUpdateForm.getWorkDesc();

        Work work = workRepository.findById(workNum).get();

        work.updateWork(workName, workPrice, workDesc);

        return work;
    }
}
