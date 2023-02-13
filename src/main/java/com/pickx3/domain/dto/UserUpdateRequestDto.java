//package com.pickx3.domain.dto;
//
//import com.pickx3.domain.entity.post_package.Post;
//import com.pickx3.domain.entity.user_package.User;
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.nio.file.*;
//import java.util.List;
//
//@Getter
//@NoArgsConstructor
//public class UserUpdateRequestDto {
//    private String userNick;
//    private String userPhone;
//    private String userIntro;
//
//    private File userImg;
//
//    @Builder
//    public UserUpdateRequestDto(String userNick, String userPhone, String userIntro, File userImg) {
//        this.userNick = userNick;
//        this.userPhone = userPhone;
//        this.userIntro = userIntro;
//        this.userImg = userImg;
//    }
//}
