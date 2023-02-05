package com.pickx3.domain.entity.work_package;

import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Builder
@Entity
public class Work {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workNum;

    private Long workerNum;

    private String workName;

    private int workPrice;

    private String workDesc;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="userNum")
    private User userInfo;

}
