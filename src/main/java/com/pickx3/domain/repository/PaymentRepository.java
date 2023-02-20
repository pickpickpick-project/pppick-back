package com.pickx3.domain.repository;


import com.pickx3.domain.entity.work_package.Payment;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
//        @Query("select p.paymentNum,p.paymentCount,p.paymentPrice,p.payMethod,p.paymentStatus,p.paymentDate,o.user.name, o.work.workNum, o.work.workName, o.work.userInfo.name from Payment p, Orders o Where p.merchantUid =o.merchantUid")
    @Query("select p,o from Payment p, Orders o Where p.merchantUid =o.merchantUid")
    List<Object[]> findSameMerchant();
}
