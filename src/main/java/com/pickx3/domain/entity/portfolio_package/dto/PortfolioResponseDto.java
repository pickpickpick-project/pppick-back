package com.pickx3.domain.entity.portfolio_package.dto;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import lombok.Getter;

import java.util.Date;

@Getter
public class PortfolioResponseDto {

    private Long id;
    private Long user;

    private String portfolioName;

    private int portfolioType;

    private Date portfolioDate;


    // Entitiy -> DTO
    public PortfolioResponseDto(Portfolio portfolio) {
        this.id = portfolio.getId();
        this.user = portfolio.getUser().getId();
        this.portfolioName = portfolio.getPortfolioName();
        this.portfolioType = portfolio.getPortfolioType();
        this.portfolioDate = portfolio.getPortfolioDate();
    }
}


