package com.pickx3.domain.entity.portfolio_package;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Favorites {

    @Column(name="favoritesNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userNum")
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;

    @Builder
    public Favorites(Long id, User user, Portfolio portfolio) {
        this.id = id;
        this.user = user;
        this.portfolio = portfolio;
    }
}
