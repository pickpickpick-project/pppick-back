package com.pickx3.domain.entity.portfolio_package.dto;

import lombok.Getter;

@Getter
public class TagResponseDto {

    private String tagName;


    // Entitiy -> DTO
    public TagResponseDto(String tagName) {
        this.tagName = tagName;
    }
}
