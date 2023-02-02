package com.pickx3.domain.entity.portfolio_package;

import javax.persistence.*;

@Entity
public class PortpolioTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioTagNum;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tagNum")
    private Tag tag;


}
