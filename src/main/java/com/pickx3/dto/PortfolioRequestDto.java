package com.pickx3.dto;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class PortfolioRequestDto {

    private Long id;

    private User user;

    private String portfolioName;

    private int portfolioType;

    private Date portfolioDate;

    // DTO -> Entity
    public Portfolio toEntity(){
        return Portfolio.builder()
                .id(id)
                .user(user)
                .portfolioDate(portfolioDate)
                .portfolioName(portfolioName)
                .portfolioType(portfolioType)
            .build();
    }
}
