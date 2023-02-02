package com.pickx3.domain.entity.portfolio_package;

import com.pickx3.domain.entity.user_package.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Portfolio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioNum;

    @ManyToOne
    @JoinColumn(name = "userNum")
    private User userInfo;

    private String portfolioName;
    private Long portfolioType;
    private Date portfolioDate;
}
