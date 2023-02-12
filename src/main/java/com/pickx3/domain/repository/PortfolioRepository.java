package com.pickx3.domain.repository;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long>, QuerydslPredicateExecutor<Portfolio> {

    List<Portfolio> findByUser_id(Long id);



}
