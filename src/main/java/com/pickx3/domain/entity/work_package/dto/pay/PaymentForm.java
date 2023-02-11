package com.pickx3.domain.entity.work_package.dto.pay;

import lombok.Data;

@Data
public class PaymentForm {
    private String merchant_uid;
    private String pg;
    private int paymentCount;
    private int paymentPrice;
    private String payMethod;

    private String buyerEmail;
    private String buyerName;
    private String buyerTel;
    private String buyerAddr;
    private String buyerPostcode;

    private Long userNum;
    private Long workNum;



}
