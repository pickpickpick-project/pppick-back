package com.pickx3.domain.entity.portfolio_package;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Tag {

    @Column(name="tagNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagNum;

    private String tagName;
    @JsonIgnore
    @OneToMany(mappedBy = "tag")
    private Set<PortfolioTag> portfolioTags;

    @Builder
    public Tag(Long tagNum, String tagName , Set<PortfolioTag> portfolioTags) {
        this.tagNum = tagNum;
        this.tagName = tagName;
        this.portfolioTags = portfolioTags;
    }
}
