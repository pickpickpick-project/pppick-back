package com.pickx3.dto;

import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import com.pickx3.domain.entity.portfolio_package.Tag;


import java.util.List;

public class TagDto {

    private Long id;

    private String tagName;

    private List<PortfolioTag> tags ;


    public Tag toEntity(){
        return Tag.builder()
                .id(id)
                .tagName(tagName)
                .tags(tags)
                .build();
    }
}
