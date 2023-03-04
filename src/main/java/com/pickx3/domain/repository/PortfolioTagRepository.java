package com.pickx3.domain.repository;

import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioTagRepository extends JpaRepository<PortfolioTag, Long> {

   // Set<PortfolioTag> findByPortfolio_id(Long id);

   // PortfolioTag findByPortfolio_PortfolioName(String name);
   List<PortfolioTag> findByTag_tagNum(Long tagNum);
}
