package com.pickx3.domain.entity.portfolio_package;

import javax.persistence.*;

@Entity
public class PortfolioImg {

    @Column(name="portfolioImgNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;

    private String portfolioImgOriginName;

    private String portfolioImgName;

    private String portfolioImgAddr;

}
