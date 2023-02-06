package com.pickx3.domain.entity.portfolio_package;

import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Portfolio {

    @Column(name="portfolioNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userNum")
    private User user;

    private String portfolioName;

    private int portfolioType;

    private Date portfolioDate;

    @Builder
    public Portfolio(Long id, User user, String portfolioName, int portfolioType, Date portfolioDate) {
        this.id = id;
        this.user = user;
        this.portfolioName = portfolioName;
        this.portfolioType = portfolioType;
        this.portfolioDate = portfolioDate;
    }
}
