package com.pickx3.service;

//import com.pickx3.domain.dto.UserUpdateRequestDto;
//import com.pickx3.domain.entity.Favorites;
import com.pickx3.domain.entity.Follow;
import com.pickx3.domain.entity.portfolio_package.Favorites;
import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.user_package.dto.UserUpdateRequestDto;
import com.pickx3.domain.repository.*;
import com.pickx3.domain.repository.post_package.PostRepository;
import com.pickx3.service.post_package.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final FavoriteRepository favoriteRepository;

    private final PortfolioRepository portfolioRepository;

    private final WorkRepository workRepository;

    private final FollowRepository followRepository;

    private final PostService postService;
    private final WorkService workService;

    private final WorkImgService workImgService;

    public User searchUserById(Long id) {
        return userRepository.findById(id).get();
    }


    @Transactional
    public User update(Long userNum,UserUpdateRequestDto requestDto) throws IOException {
        String userNickname = requestDto.getUserNick();
        String userIntro = requestDto.getUserIntro();
        String userPhone = requestDto.getUserPhone();


        User user = userRepository.findById(userNum).get();

        user.updateUser(userNickname, userIntro,userPhone);

        MultipartFile file = requestDto.getUserImg();



        if(file!=null){
            if(user.getImageUrl()!=null){
                if(user.getImageUrl().startsWith("images")){
                    String absolutePath = new File("").getAbsolutePath() + File.separator;
                    Path filePath = Paths.get(absolutePath+user.getImageUrl());
                    Files.delete(filePath);
                }
                user.setImageUrl(null);
            }

            // 파일명을 업로드 한 날짜로 변환하여 저장
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            // 프로젝트 디렉터리 내의 저장을 위한 절대 경로 설정
            // 경로 구분자 File.separator 사용
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;


            // 파일을 저장할 세부 경로 지정
            String path = "images" + File.separator + "user" + File.separator + current_date+File.separator;

            File newFile = new File(path);
            // 디렉터리가 존재하지 않을 경우
            if(!newFile.exists()) {
                boolean wasSuccessful = newFile.mkdirs();

                // 디렉터리 생성에 실패했을 경우
                if(!wasSuccessful)
                    System.out.println("file: was not successful");
            }

            //확장자 처리
            String originalFileExtension = null;
            String contentType = file.getContentType();

            // 확장자명이 존재하지 않을 경우 처리 x
            if(!ObjectUtils.isEmpty(contentType)) {
                if(contentType.contains("image/jpeg"))
                    originalFileExtension = ".jpg";
                else if(contentType.contains("image/png"))
                    originalFileExtension = ".png";
            }

            // 파일명 중복 피하고자 나노초까지 얻어와 지정
            String new_file_name = System.nanoTime() + originalFileExtension;
            newFile = new File(absolutePath + path + File.separator + new_file_name);
            file.transferTo(newFile);
            // 파일 권한 설정(쓰기, 읽기)
            newFile.setWritable(true);
            newFile.setReadable(true);

            user.setImageUrl(path+new_file_name);

        }

        return user;

    }
    @Transactional
    public void delete(Long userNum) throws IOException {
        User user = userRepository.findById(userNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 구독 내역 삭제
        List<Follow> follows = followRepository.findByFollowerNum(userNum);
        if(follows.size()!=0){
            for(Follow f: follows){
                followRepository.delete(f);
            }

        }
        List<Follow> follows2 = followRepository.findByFollowingNum(userNum);
        if(follows2.size()!=0){
            for(Follow f: follows2){
                followRepository.delete(f);
            }

        }

        //유저 지우면 썼던 포스트들 삭제
        List<Post> postss = postRepository.findByUser_Id(userNum);
        if(postss.size()!=0){
            for(Post post : postss){
                postService.delete(post.getPostNum());
            }

        }
        // 유저 지우면 개인 유저 문의게시판 삭제
        List<Post> posts = postRepository.findByPostBoardNum(userNum);
        if(posts.size()!=0){
            for(Post post : posts){
                postService.delete(post.getPostNum());
            }

        }


        List<Favorites> favorites = favoriteRepository.findByUser_id(userNum);
        if(favorites.size()!=0){
            for(Favorites favorite : favorites){
                favoriteRepository.delete(favorite);
            }
        }

        List<Portfolio> portfolios = portfolioRepository.findByUser_id(userNum);
        if(portfolios.size()!=0){
            for(Portfolio portfolio : portfolios){
                portfolioRepository.delete(portfolio);
            }
        }

        List<Long> workNums = workRepository.findByUserInfo_id(userNum).stream().map(o->o.getWorkNum()).collect(Collectors.toList());
        if(workNums.size()!=0){
            for(Long workNum : workNums){
                System.out.println(workNum);
                workImgService.removeWorkImages(workNum);
                workService.removeWork(workNum);
            }
        }


        userRepository.delete(user);

    }

}
