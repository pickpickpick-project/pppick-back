package com.pickx3.domain.entity.portfolio_package;

import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {

    @Column(name="tagNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

    @OneToMany(mappedBy = "id")
    private List<PortfolioTag> tags = new ArrayList<>();

    @Builder
    public Tag(Long id, String tagName, List<PortfolioTag> tags) {
        this.id = id;
        this.tagName = tagName;
        this.tags = tags;
    }
}
