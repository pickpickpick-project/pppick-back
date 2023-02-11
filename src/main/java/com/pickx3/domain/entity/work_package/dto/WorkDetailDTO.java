package com.pickx3.domain.entity.work_package.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class WorkDetailDTO {
    private Long workerNum;

    private String workName;

    private String workDesc;

    private int workPrice;

    private List<WorkImgForm> workImages;
}
