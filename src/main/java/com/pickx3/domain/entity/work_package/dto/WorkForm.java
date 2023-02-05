package com.pickx3.domain.entity.work_package.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WorkForm {
    private Long workerNum;
    private String workName;
    private String workDesc;
    private int workPrice;
}
