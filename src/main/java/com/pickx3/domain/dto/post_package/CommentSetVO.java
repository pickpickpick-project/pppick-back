package com.pickx3.domain.dto.post_package;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Data
public class CommentSetVO {
    private String userNum;
    private String commentContent;
}
