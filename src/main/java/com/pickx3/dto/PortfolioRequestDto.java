package com.pickx3.dto;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import com.pickx3.domain.entity.portfolio_package.Tag;
import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PortfolioRequestDto {

    private Long id;

    private User userNum;

    private String portfolioName;

    private int portfolioType;

    private Date portfolioDate;

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
