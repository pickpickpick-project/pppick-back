package com.pickx3.domain.entity.work_package.dto.orders;

import com.pickx3.domain.entity.work_package.dto.pay.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDetailDTO {
    private Long orderNum;
    private int orderCount;

    private OrderStatus orderStatus;
    private String merchantUid;

    private String workName;
    private int workPrice;

    private int paymentPrice;
    private String payMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;




//    private String buyerEmail;
//    private String buyerName;
//    private String buyerTel;
//    private String buyerAddr;
//    private String buyerPostcode;
}
