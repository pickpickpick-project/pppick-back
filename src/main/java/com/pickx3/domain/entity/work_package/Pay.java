package com.pickx3.domain.entity.work_package;

import com.pickx3.domain.entity.user_package.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pay{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payNum;

    private String merchant_uid;

    private LocalDate payDate;

    private int payPrice;

    private String payMethod;

    private int payCount;

    private int payStatus;

    private String pg;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="payerNum")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="workNum")
    private Work work;
}
