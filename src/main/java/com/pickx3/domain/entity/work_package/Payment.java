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
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentNum;

    private String merchant_uid;

    private LocalDate paymentDate;

    private int paymentPrice;

    private String payMethod;

    private int paymentCount;

    private int paymentStatus;

    private String pg;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="payerNum")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="workNum")
    private Work work;
}
