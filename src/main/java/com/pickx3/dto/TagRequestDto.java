package com.pickx3.dto;

import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import com.pickx3.domain.entity.portfolio_package.Tag;

import java.util.Set;

public class TagRequestDto {

    private Long id;

    private String tagName;

    private Set<PortfolioTag> tags ;

    // DTO -> Entity
    public Tag toEntity(){
        return Tag.builder()
                .id(id)
                .tagName(tagName)
                .tags(tags)
                .build();
    }
}
