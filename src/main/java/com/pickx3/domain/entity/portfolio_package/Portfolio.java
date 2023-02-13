package com.pickx3.domain.entity.portfolio_package;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pickx3.domain.entity.user_package.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@Getter
@NoArgsConstructor
@Entity
public class Portfolio {

    @Column(name="portfolioNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userNum")
    private User user;

    private String portfolioName;

    private int portfolioType;
    @CreationTimestamp
    private Date portfolioDate;

    /* 연관관계 */
    @JsonIgnore
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<PortfolioImg> portfolioImgList;

    /* 연관관계 */
    @OneToMany(mappedBy = "portfolio")
    private Set<PortfolioTag> portfolioTagList;

    /* 연관관계 */  //N+1 SET 으로 해결
    @JsonIgnore
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    public Set<Favorites> favorites;

    @Builder
    public Portfolio(User user, String portfolioName, int portfolioType,
                     Date portfolioDate, Set<PortfolioTag> portfolioTagList) {
        this.user = user;
        this.portfolioName = portfolioName;
        this.portfolioType = portfolioType;
        this.portfolioDate = portfolioDate;
        this.portfolioTagList = portfolioTagList;
    }
}


