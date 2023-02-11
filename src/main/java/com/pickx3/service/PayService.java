package com.pickx3.service;

import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.work_package.Pay;
import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.dto.pay.PayForm;
import com.pickx3.domain.repository.PayRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PayService {
    @Autowired
    private PayRepository payRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkRepository workRepository;

    public void payWork(PayForm payForm){
        User user = userRepository.findById(payForm.getUserNum()).get();
        Work work = workRepository.findById(payForm.getWorkNum()).get();

        Pay pay  = Pay.builder()
                .payDate(LocalDate.now())
                .payPrice(payForm.getPayPrice())
                .payCount(payForm.getPayCount())
                .payMethod(payForm.getPayMethod())
                .pg(payForm.getPg())
                .merchant_uid(payForm.getMerchant_uid())
                .user(user)
                .work(work)
                .build();

        payRepository.save(pay);
    }
}
