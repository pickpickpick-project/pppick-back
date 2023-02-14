package com.pickx3.domain.entity.portfolio_package.dto;

import com.pickx3.domain.entity.portfolio_package.Tag;

public class TagRequestDto {

    private String tagName;

   // private Set<PortfolioTag> tags ;

    // DTO -> Entity
    public Tag toEntity(){
        return Tag.builder()
                .tagName(tagName)
                //.tags(tags)
                .build();
    }
}
