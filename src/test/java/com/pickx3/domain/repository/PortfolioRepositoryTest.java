package com.pickx3.domain.repository;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PortfolioRepositoryTest {

    @Autowired
    PortfolioRepository portfolioRepository;

    @Test
    @DisplayName("이벤트 레포지토리 : 이벤트 등록")
    void 이벤트_등록() {
        // given
        String portfolioName = "adsasd";
        int portfolioType = 1;

        portfolioRepository.save(Portfolio.builder()
                .portfolioName(portfolioName)
                .portfolioType(portfolioType)
                .build());
        // when
        List<Portfolio> portfolioList = portfolioRepository.findAll();

        // then
        Portfolio portfolio = portfolioList.get(0);
        assertThat(portfolio.getPortfolioName()).isEqualTo(portfolioName);
        assertThat(portfolio.getPortfolioType()).isEqualTo(portfolioType);
    }
}