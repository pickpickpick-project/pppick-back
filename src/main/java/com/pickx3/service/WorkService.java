package com.pickx3.service;

import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.dto.WorkForm;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.WorkRepository;
import net.bytebuddy.asm.MemberRemoval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class WorkService {
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private UserRepository userRepository;

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
}
