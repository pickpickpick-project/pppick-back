package com.pickx3.domain.entity.portfolio_package;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class PortfolioTag {

    @Column(name="portfolioTagNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tagNum")
    private Tag tag;

    @Builder
    public PortfolioTag(Long id, Portfolio portfolio, Tag tag) {
        this.id = id;
        this.portfolio = portfolio;
        this.tag = tag;
    }
}
