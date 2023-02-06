package com.pickx3.dto;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class PortfolioRequestDto {

    private Long id;

    private User userNum;

    private String portfolioName;

    private int portfolioType;

    private Date portfolioDate;

    @Builder
    public PortfolioRequestDto(Long id, User userNum, String portfolioName, int portfolioType, Date portfolioDate) {
        this.id = id;
        this.userNum = userNum;
        this.portfolioName = portfolioName;
        this.portfolioType = portfolioType;
        this.portfolioDate = portfolioDate;
    }

    // DTO -> Entity
    public Portfolio toEntity(){
        return Portfolio.builder()
                .id(id)
                .user(userNum)
                .portfolioDate(portfolioDate)
                .portfolioName(portfolioName)
                .portfolioType(portfolioType)
            .build();
    }
}
