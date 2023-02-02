package com.pickx3.domain.entity.work_package;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WorkImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workImgNum;

    private Long workNum;

    private String workImgOriginName;

    private String workImgName;

    private String workImgSrcPath;

}
