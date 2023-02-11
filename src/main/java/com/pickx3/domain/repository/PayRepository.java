package com.pickx3.domain.repository;

import com.pickx3.domain.entity.work_package.Payment;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayRepository extends JpaRepository<Payment, Long> {
    List<PaymentForm> findByUser_id(Long userNum);
}
