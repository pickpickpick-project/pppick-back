package com.pickx3.domain.entity.portfolio_package;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Tag {

    @Column(name="tagNum")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

    @OneToMany(mappedBy = "tag")
    private Set<PortfolioTag> tags = new HashSet<>();

    @Builder
    public Tag(Long id, String tagName, Set<PortfolioTag> tags) {
        this.id = id;
        this.tagName = tagName;
        this.tags = tags;
    }
}
