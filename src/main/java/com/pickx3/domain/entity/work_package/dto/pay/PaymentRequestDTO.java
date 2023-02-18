package com.pickx3.domain.entity.work_package.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {
    private String merchantUid;
    private String pg;
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
