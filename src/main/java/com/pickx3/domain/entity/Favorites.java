package com.pickx3.domain.entity;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;

import javax.persistence.*;

@Entity
public class Favorites {

    @Column(name="favoritesNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userNum")
    private User user;

    @ManyToOne
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;
}
