package com.pickx3.domain.entity.portfolio_package.dto;

import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import lombok.Getter;

import java.util.Set;

@Getter
public class TagResponseDto {

    private String tagName;

    private Set<PortfolioTag> tags ;


    // Entitiy -> DTO
    public TagResponseDto(String tagName, Set<PortfolioTag> tags) {
        this.tagName = tagName;
        this.tags = tags;
    }
}
