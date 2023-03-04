package com.pickx3.domain.entity.work_package.dto.pay;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyDTO {
    private String merchantUid;
    private String imp_uid;
}
