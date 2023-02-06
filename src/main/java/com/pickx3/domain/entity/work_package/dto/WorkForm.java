package com.pickx3.domain.entity.work_package.dto;

import com.pickx3.domain.entity.work_package.Work;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Getter
@NoArgsConstructor
public class WorkForm {
    private Long workerNum;

    @NotBlank(message = "상품명을 입력해주세요")
    private String workName;

    private String workDesc;

    @PositiveOrZero
    @Min(0)
    @NotNull(message="상품가격을 입력해 주세요")
    private int workPrice;
}
