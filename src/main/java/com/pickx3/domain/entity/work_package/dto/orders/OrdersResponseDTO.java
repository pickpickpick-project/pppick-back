package com.pickx3.domain.entity.work_package.dto.orders;

import com.pickx3.domain.entity.work_package.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrdersResponseDTO {
    private Long orderNum;
    private String merchantUid;
    private String workName;
    private int workPrice;
    private int orderCount;
    private int orderPrice;
    private LocalDateTime orderDate;

//    private String buyerEmail;
//    private String buyerName;
//    private String buyerTel;
//    private String buyerAddr;
//    private String buyerPostcode;
}
