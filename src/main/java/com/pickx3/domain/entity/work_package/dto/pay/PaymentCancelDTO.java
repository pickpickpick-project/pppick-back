package com.pickx3.domain.entity.work_package.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentCancelDTO {
    private String merchantUid;
    private int cancelRequestAmount;
}
