package com.pickx3.domain.entity.user_package.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Data
public class UserLoginRequestDto {
    private String id;
    private String password;
}