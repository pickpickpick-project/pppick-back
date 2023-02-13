package com.pickx3.domain.entity.portfolio_package.dto;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import com.pickx3.domain.entity.user_package.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class PortfolioRequestDto {

    private User user;

    private String portfolioName;

    private int portfolioType;

    private Date portfolioDate;

    private Set<PortfolioTag> portfolioTag;

    // DTO -> Entity
    public Portfolio toEntity(){
        return Portfolio.builder()
                .user(user)
                .portfolioDate(portfolioDate)
                .portfolioName(portfolioName)
                .portfolioType(portfolioType)
                .portfolioTagList(portfolioTag)
            .build();
    }
}
