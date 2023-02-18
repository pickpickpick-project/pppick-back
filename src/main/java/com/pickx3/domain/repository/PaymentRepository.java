package com.pickx3.domain.repository;


import com.pickx3.domain.entity.work_package.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
//    List<PaymentRequestDTO> findByUser_id(Long userNum);
}
