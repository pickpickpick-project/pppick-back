package com.pickx3.domain.repository;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {


}
