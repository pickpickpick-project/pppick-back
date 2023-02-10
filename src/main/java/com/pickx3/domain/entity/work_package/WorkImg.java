package com.pickx3.domain.entity.work_package;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class WorkImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workImgNum;

    private String workImgOriginName;

    private String workImgName;

    private String workImgSrcPath;

    @ManyToOne
    @JoinColumn(name = "workNum")
    private Work work;


}
