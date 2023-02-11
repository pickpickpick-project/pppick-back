package com.pickx3.dto;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class PortfolioResponseDto {

    private Long id;
    private User user;

    private String portfolioName;

    private int portfolioType;

    private Date portfolioDate;



    // Entitiy -> DTO
    public PortfolioResponseDto(Portfolio portfolio) {
        this.id = portfolio.getId();
        this.user = portfolio.getUser();
        this.portfolioName = portfolio.getPortfolioName();
        this.portfolioType = portfolio.getPortfolioType();
        this.portfolioDate = portfolio.getPortfolioDate();
    }
}


