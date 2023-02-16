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
    private Long id;

    private String tagName;
    @JsonIgnore
    @OneToMany(mappedBy = "tag")
    private Set<PortfolioTag> portfolioTags;

    @Builder
    public Tag(Long id, String tagName , Set<PortfolioTag> portfolioTags) {
        this.id = id;
        this.tagName = tagName;
        this.portfolioTags = portfolioTags;
    }
}
