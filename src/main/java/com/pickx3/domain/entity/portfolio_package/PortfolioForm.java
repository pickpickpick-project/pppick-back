package com.pickx3.domain.entity.portfolio_package;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PortfolioForm {

    private Long userNum;

    private String portfolioName;

    private String portfolioType;

    private String portfolioDate;

    //@Schema(title = "이미지 파일", description = "이미지 파일")
    //private List<PortfolioImg> portfolioImgList;

    //private Set<PortfolioTag> portfolioTagLis;
}
