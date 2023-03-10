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

            // ???????????? ????????? ??? ????????? ???????????? ??????
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            // ???????????? ???????????? ?????? ????????? ?????? ?????? ?????? ??????
            // ?????? ????????? File.separator ??????
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;


            // ????????? ????????? ?????? ?????? ??????
            String path = "images" + File.separator + "user" + File.separator + current_date+File.separator;

            File newFile = new File(path);
            // ??????????????? ???????????? ?????? ??????
            if(!newFile.exists()) {
                boolean wasSuccessful = newFile.mkdirs();

                // ???????????? ????????? ???????????? ??????
                if(!wasSuccessful)
                    System.out.println("file: was not successful");
            }

            //????????? ??????
            String originalFileExtension = null;
            String contentType = file.getContentType();

            // ??????????????? ???????????? ?????? ?????? ?????? x
            if(!ObjectUtils.isEmpty(contentType)) {
                if(contentType.contains("image/jpeg"))
                    originalFileExtension = ".jpg";
                else if(contentType.contains("image/png"))
                    originalFileExtension = ".png";
            }

            // ????????? ?????? ???????????? ??????????????? ????????? ??????
            String new_file_name = System.nanoTime() + originalFileExtension;
            newFile = new File(absolutePath + path + File.separator + new_file_name);
            file.transferTo(newFile);
            // ?????? ?????? ??????(??????, ??????)
            newFile.setWritable(true);
            newFile.setReadable(true);

            user.setImageUrl(path+new_file_name);

        }

        return user;

    }
    @Transactional
    public void delete(Long userNum) throws IOException {
        User user = userRepository.findById(userNum)
                .orElseThrow(() -> new IllegalArgumentException("?????? ????????? ???????????? ????????????."));

        // ?????? ?????? ??????
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

        //?????? ????????? ?????? ???????????? ??????
        List<Post> postss = postRepository.findByUser_Id(userNum);
        if(postss.size()!=0){
            for(Post post : postss){
                postService.delete(post.getPostNum());
            }

        }
        // ?????? ????????? ?????? ?????? ??????????????? ??????
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
