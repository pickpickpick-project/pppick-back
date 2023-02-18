package com.pickx3.domain.entity.work_package.dto.pay;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Long paymentId;
    private String merchantUid;
    private String pg;
    private int paymentCount;
    private int paymentPrice;
    private String payMethod;
    private int paymentStatus;

    private Long payerNum;
    private Long workNum;

//    private String buyerEmail;
//    private String buyerName;
//    private String buyerTel;
//    private String buyerAddr;
//    private String buyerPostcode;
}
