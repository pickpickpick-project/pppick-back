package com.pickx3.domain.entity.work_package.dto.work;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class WorkUpdateForm {
    private Long workNum;
    private Long workerNum;

    @NotBlank(message = "상품명을 입력해주세요")
    private String workName;
    private String workDesc;
    
    @PositiveOrZero
    @Min(0)
    @NotNull(message="상품가격을 입력해 주세요")
    private int workPrice;

    @Schema(title = "이미지 파일", description = "이미지 파일")
    private List<MultipartFile> files;
}
