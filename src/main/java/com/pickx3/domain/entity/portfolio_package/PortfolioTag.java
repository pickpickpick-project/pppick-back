package com.pickx3.domain.entity.portfolio_package;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class PortfolioTag {

    @Column(name="portfolioTagNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tagNum")
    private Tag tag;



}
