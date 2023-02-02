package com.pickx3.domain.entity;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;

import javax.persistence.*;

@Entity
public class Favorites {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoritesNum;

    @ManyToOne
    @JoinColumn(name = "userNum")
    private User userInfo;

    @ManyToOne
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;
}
