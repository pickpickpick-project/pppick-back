package com.pickx3.domain.repository;

import com.pickx3.domain.entity.portfolio_package.PortfolioImg;
import com.pickx3.domain.entity.portfolio_package.PortfolioImgForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioImgRepository extends JpaRepository<PortfolioImg, Long> {

    List<PortfolioImgForm> findByPortfolio_id(Long id);


}
