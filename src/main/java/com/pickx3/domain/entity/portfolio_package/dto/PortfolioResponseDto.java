package com.pickx3.domain.entity.portfolio_package.dto;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.PortfolioImg;
import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
public class PortfolioResponseDto {

    private Long id;
    private Long user;
    private String portfolioName;
    private int portfolioType;
    private Date portfolioDate;

    private List<PortfolioImg> portfolioImgList;
    private Set<PortfolioTag> PortfolioTags;


    // Entitiy -> DTO
    public PortfolioResponseDto(Portfolio portfolio) {
        this.id = portfolio.getId();
        this.user = portfolio.getUser().getId();
        this.portfolioName = portfolio.getPortfolioName();
        this.portfolioType = portfolio.getPortfolioType();
        this.portfolioDate = portfolio.getPortfolioDate();
        this.portfolioImgList = portfolio.getPortfolioImgList();
        this.PortfolioTags = portfolio.getPortfolioTagList();
    }
}


