package com.pickx3.domain.entity.work_package;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentNum;
    private String merchantUid;
    private String pg;
    private int paymentCount;
    private int paymentPrice;
    private String payMethod;
    private LocalDate paymentDate;
    private int paymentStatus;

//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name="payerNum")
//    private User user;
//
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name="workNum")
//    private Work work;
}
