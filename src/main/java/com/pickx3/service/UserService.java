package com.pickx3.service;

//import com.pickx3.domain.dto.UserUpdateRequestDto;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.post_package.Post;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.FavoriteRepository;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.WorkRepository;
import com.pickx3.domain.repository.post_package.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    private final WorkService workService;

    private final WorkImgService workImgService;

    public User searchUserById(Long id) {
        return userRepository.findById(id).get();
    }


//    @Transactional
//    public Long update(UserUpdateRequestDto requestDto) throws IOException {
//        User user = userRepository.findById(requestDto.getUserNum())
//                .orElseThrow(() -> new
//                        IllegalArgumentException("해당 유저가 존재하지 않습니다."));
//        user.setNickName(requestDto.getUserNick());
//        user.setPhone(requestDto.getUserPhone());
//        user.setIntro(requestDto.getUserIntro());
//
//        File file = requestDto.getUserImg();
//
//
//
//        if(file!=null){
//            // 파일명을 업로드 한 날짜로 변환하여 저장
//            LocalDateTime now = LocalDateTime.now();
//            DateTimeFormatter dateTimeFormatter =
//                    DateTimeFormatter.ofPattern("yyyyMMdd");
//            String current_date = now.format(dateTimeFormatter);
//
//            // 프로젝트 디렉터리 내의 저장을 위한 절대 경로 설정
//            // 경로 구분자 File.separator 사용
//            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
//
//
//            // 파일을 저장할 세부 경로 지정
//            String path = "images" + File.separator + "user" + File.separator + current_date+File.separator;
//
//            File newFile = new File(path);
//            // 디렉터리가 존재하지 않을 경우
//            if(!newFile.exists()) {
//                boolean wasSuccessful = newFile.mkdirs();
//
//                // 디렉터리 생성에 실패했을 경우
//                if(!wasSuccessful)
//                    System.out.println("file: was not successful");
//            }
//
//            //확장자 처리
//            String originalFileExtension = null;
//            String fileName = file.getName();
//            String contentType = fileName.substring(fileName.lastIndexOf(".") + 1);
//
//            // 확장자명이 존재하지 않을 경우 처리 x
//            if(!ObjectUtils.isEmpty(contentType)) {
//                if(contentType.contains("image/jpeg"))
//                    originalFileExtension = ".jpg";
//                else if(contentType.contains("image/png"))
//                    originalFileExtension = ".png";
//            }
//
//            // 파일명 중복 피하고자 나노초까지 얻어와 지정
//            String new_file_name = System.nanoTime() + originalFileExtension;
//            newFile = new File(absolutePath + path + File.separator + new_file_name);
//
//            // 파일 권한 설정(쓰기, 읽기)
//            newFile.setWritable(true);
//            newFile.setReadable(true);
//
//            user.setImageUrl(newFile.getPath());
//
//        }
//
//        userRepository.save(user);
//
//
//        return requestDto.getUserNum();
//
//    }
    @Transactional
    public void delete(Long userNum) throws IOException {
        User user = userRepository.findById(userNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 유저 지우면 개인 유저 문의게시판 삭제
        List<Post> posts = postRepository.findByPostBoardNum(userNum);
        if(posts.size()!=0){
            for(Post post : posts){
                postRepository.delete(post);
            }

        }

        //유저 지우면 썼던 포스트들 삭제
//        List<Post> postss = postRepository.findByUser_Id(userNum);
//        if(posts.size()!=0){
//            for(Post post : postss){
//                postRepository.delete(post);
//            }
//
//        }
/*
        List<Favorites> favorites = favoriteRepository.findByUser_id(userNum);
        if(favorites.size()!=0){
            for(Favorites favorite : favorites){
                favoriteRepository.delete(favorite);
            }
        }
*/
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
