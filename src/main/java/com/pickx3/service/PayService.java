package com.pickx3.service;

import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.work_package.Payment;
import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentForm;
import com.pickx3.domain.repository.PayRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayService {
    @Autowired
    private PayRepository payRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkRepository workRepository;

    public void payWork(PaymentForm paymentForm){
        User user = userRepository.findById(paymentForm.getUserNum()).get();
        Work work = workRepository.findById(paymentForm.getWorkNum()).get();

        Payment payment = Payment.builder()
                .paymentDate(LocalDate.now())
                .paymentPrice(paymentForm.getPaymentPrice())
                .paymentCount(paymentForm.getPaymentCount())
                .payMethod(paymentForm.getPayMethod())
                .pg(paymentForm.getPg())
                .merchant_uid(paymentForm.getMerchant_uid())
                .user(user)
                .work(work)
                .build();

        payRepository.save(payment);
    }

    public List<PaymentForm> getPaymentHistory(Long userNum){
        return payRepository.findByUser_id(userNum);
    }
}
