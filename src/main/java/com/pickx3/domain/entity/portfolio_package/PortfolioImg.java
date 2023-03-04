package com.pickx3.domain.entity.portfolio_package;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioImgNum;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;

    @Column(name="portfolioImgOriginName")
    private String portfolioImgOriginName;  //원본

    @Column(name="portfolioImgName")
    private String portfolioImgName;   //서버저장이름

    private String portfolioImgAddr;


    @Builder
    public PortfolioImg(Long portfolioImgNum, Portfolio portfolio, String portfolioImgOriginName, String portfolioImgName, String portfolioImgAddr) {
        this.portfolioImgNum = portfolioImgNum;
        this.portfolio = portfolio;
        this.portfolioImgOriginName = portfolioImgOriginName;
        this.portfolioImgName = portfolioImgName;
        this.portfolioImgAddr = portfolioImgAddr;
    }
}
