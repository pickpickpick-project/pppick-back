package com.pickx3.domain.entity.work_package.dto.orders;

import com.pickx3.domain.entity.work_package.Work;
import lombok.Data;

@Data
public class OrdersResponseDTO {
    private Long orderId;
    private String merchant_uid;
    private int order_count;
    private int order_price;

    private Work work;

//    private String buyerEmail;
//    private String buyerName;
//    private String buyerTel;
//    private String buyerAddr;
//    private String buyerPostcode;
}
