package com.pickx3.domain.entity.portfolio_package;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioForm {

    private Long userNum;

    private String portfolioName;

    private String portfolioType;

    private String portfolioDate;

    @Schema(title = "이미지 파일", description = "이미지 파일")
    private List<MultipartFile> files;


    // private List<PortfolioImg> portfolioImgList;

    //private Set<PortfolioTag> portfolioTagLis;
}
