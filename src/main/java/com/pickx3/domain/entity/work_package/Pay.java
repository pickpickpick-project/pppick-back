package com.pickx3.domain.entity.work_package;

import com.pickx3.domain.entity.user_package.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pay{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payNum;

    private Date payDate;

    private int payPrice;

    private String payMethod;

    private int payCount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="payerNum")
    private User userInfo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="workNum")
    private Work work;
}
