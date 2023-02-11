package com.pickx3.domain.entity.work_package.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Schema
@Getter
@Setter
public class WorkImgForm {
    private String workImgName;
    private String workImgOriginName;
    private String workImgSrcPath;
//    private long size;

    @JsonIgnore
    @Schema(title = "이미지 파일", description = "이미지 파일")
    private List<MultipartFile> files;

    public WorkImgForm(String workImgName, String workImgOriginName, String workImgSrcPath){
        this.workImgName = workImgName;
        this.workImgOriginName = workImgOriginName;
        this.workImgSrcPath = workImgSrcPath;
    }
}
