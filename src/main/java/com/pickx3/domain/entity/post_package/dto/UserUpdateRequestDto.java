//package com.pickx3.domain.dto;
//
//import com.pickx3.domain.entity.post_package.Post;
//import com.pickx3.domain.entity.user_package.User;
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Builder;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.nio.file.*;
//import java.util.List;
//
//@Getter
//@Data
//@Schema(title = "유저파일 Vo")
//public class UserUpdateRequestDto {
//    private Long userNum;
//    private String userNick;
//    private String userPhone;
//    private String userIntro;
//
//    @Schema(title = "유저 File Array", description = "유저 File Array")
//    private File userImg;
//
//    @Builder
////    public UserUpdateRequestDto(String userNick, String userPhone, String userIntro, File userImg) {
//    public UserUpdateRequestDto(Long userNum, String userNick, String userPhone, String userIntro,File userImg) {
//        this.userNum = userNum;
//        this.userNick = userNick;
//        this.userPhone = userPhone;
//        this.userIntro = userIntro;
//
//        this.userImg = userImg;
//    }
//}
