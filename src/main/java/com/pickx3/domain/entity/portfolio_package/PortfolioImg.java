package com.pickx3.domain.entity.portfolio_package;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class PortfolioImg {

    @Column(name="portfolioImgNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;

    @Column(name="portfolioImgOriginName")
    private String portfolioImgOriginName;  //원본

    @Column(name="portfolioImgName")
    private String portfolioImgName;   //서버저장이름

    private String portfolioImgAddr;


    @Builder
    public PortfolioImg(Long id, Portfolio portfolio, String portfolioImgOriginName, String portfolioImgName, String portfolioImgAddr) {
        this.id = id;
        this.portfolio = portfolio;
        this.portfolioImgOriginName = portfolioImgOriginName;
        this.portfolioImgName = portfolioImgName;
        this.portfolioImgAddr = portfolioImgAddr;
    }
}
