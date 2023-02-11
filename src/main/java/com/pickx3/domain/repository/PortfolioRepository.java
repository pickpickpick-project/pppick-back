package com.pickx3.domain.repository;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findByUser_id(Long id);


//    List<Portfolio> findByFavoritesAndUser(Favorites favorites, User user);
}
