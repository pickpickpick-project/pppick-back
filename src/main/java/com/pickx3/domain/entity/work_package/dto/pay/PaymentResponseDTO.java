package com.pickx3.domain.entity.work_package.dto.pay;

import com.pickx3.domain.entity.work_package.Orders;
import com.pickx3.domain.entity.work_package.Payment;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentResponseDTO {
    private Long paymentId;
//    private String merchantUid;
//    private String pg;
    private int paymentCount;
    private int paymentPrice;
    private String payMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;

    private String payerName;
    private Long workNum;
    private String workName;
    private String workerName;
//    private String buyerEmail;
//    private String buyerName;
//    private String buyerTel;
//    private String buyerAddr;
//    private String buyerPostcode;
//    public PaymentResponseDTO(Payment entity){
//        this.paymentId = entity.getPaymentNum();
//        this.merchantUid = entity.getMerchantUid();
//        this.paymentCount = entity.getPaymentCount();
//        this.paymentPrice = entity.getPaymentPrice();
//        this.payMethod = entity.getPayMethod();
//        this.paymentStatus = entity.getPaymentStatus();
//
//    }

    public PaymentResponseDTO (Payment p, Orders o){
        this.paymentId =p.getPaymentNum();
        this.paymentCount = p.getPaymentCount();
        this.paymentPrice=p.getPaymentPrice();
        this.payMethod=p.getPayMethod();
        this.paymentStatus = p.getPaymentStatus();
        this.paymentDate=p.getPaymentDate();

        this.payerName=o.getUser().getName();
        this.workNum=o.getWork().getWorkNum();
        this.workName=o.getWork().getWorkName();
        this.workerName=o.getWork().getUserInfo().getName();

    }
}
