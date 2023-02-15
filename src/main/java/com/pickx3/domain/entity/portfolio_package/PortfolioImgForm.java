package com.pickx3.domain.entity.portfolio_package;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Schema
@Setter
@Getter
public class PortfolioImgForm {

    private Long id;

    private String portfolioImgOriginName;  //원본

    private String portfolioImgName;   //서버저장이름

    private String portfolioImgAddr;

    private long size;

    @JsonIgnore
    @Schema(title = "이미지 파일", description = "이미지 파일")
    private List<MultipartFile> files;

    public PortfolioImgForm(Long id, String portfolioImgOriginName, String portfolioImgName,
                            String portfolioImgAddr) {
        this.id = id;
        this.portfolioImgOriginName = portfolioImgOriginName;
        this.portfolioImgName = portfolioImgName;
        this.portfolioImgAddr = portfolioImgAddr;
    }
}
