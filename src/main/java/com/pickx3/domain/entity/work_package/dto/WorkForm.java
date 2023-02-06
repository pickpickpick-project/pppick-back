package com.pickx3.domain.entity.work_package.dto;

import com.pickx3.domain.entity.work_package.Work;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class WorkForm {
    private Long workerNum;
    private String workName;
    private String workDesc;
    private int workPrice;
}
