package com.pickx3.domain.entity.work_package.dto.pay;

import lombok.Data;

@Data
public class PayForm {
    private String merchant_uid;
    private String pg;
    private int payCount;
    private int payPrice;
    private String payMethod;

    private String buyerEmail;
    private String buyerName;
    private String buyerTel;
    private String buyerAddr;
    private String buyerPostcode;

    private Long userNum;
    private Long workNum;



}
