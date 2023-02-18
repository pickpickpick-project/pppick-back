package com.pickx3.domain.entity.work_package.dto.orders;

import lombok.Data;

@Data
public class OrdersRequestDTO {
    private String merchantUid;
    private int orderCount;
    private int orderPrice;

    private Long userNum;
    private Long workNum;

}
