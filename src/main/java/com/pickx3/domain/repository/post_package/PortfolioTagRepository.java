package com.pickx3.domain.repository.post_package;

import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioTagRepository extends JpaRepository<PortfolioTag, Long> {

   // Set<PortfolioTag> findByPortfolio_id(Long id);

   // PortfolioTag findByPortfolio_PortfolioName(String name);
}
