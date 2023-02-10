package com.pickx3.domain.entity.work_package.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Schema
@Getter
@Setter
public class WorkImgForm {
    private String fileName;
    private String fileOriginalName;
    private String fileUrl;
    private long size;

    @Schema(title = "이미지 파일", description = "이미지 파일")
    private List<MultipartFile> files;

    public WorkImgForm(String fileName, String fileOriginalName, String fileUrl){
        this.fileName = fileName;
        this.fileOriginalName = fileOriginalName;
        this.fileUrl = fileUrl;
    }
}
