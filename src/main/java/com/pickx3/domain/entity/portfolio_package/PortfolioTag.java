package com.pickx3.domain.entity.portfolio_package;

import javax.persistence.*;
import java.util.List;

@Entity
public class PortfolioTag {

    @Column(name="portfolioTagNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolioNum")
    private Portfolio portfolio;

    @OneToMany
    @JoinColumn(name = "tagNum")
    private List<Tag> tag;


}
