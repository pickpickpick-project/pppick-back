package com.pickx3.domain.entity.work_package;

import com.pickx3.domain.entity.user_package.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNum;
    private String merchantUid;
    private int orderCount;
    private int orderPrice;
    private LocalDateTime orderDate;
    private int orderStatus;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="userNum")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="workNum")
    private Work work;
}
