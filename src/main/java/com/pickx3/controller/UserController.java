package com.pickx3.controller;

//import com.pickx3.domain.dto.UserUpdateRequestDto;

import com.pickx3.domain.entity.FollowForm;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.user_package.dto.UserUpdateRequestDto;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.exception.ResourceNotFoundException;
import com.pickx3.security.CurrentUser;
import com.pickx3.security.UserPrincipal;
import com.pickx3.security.token.TokenProvider;
import com.pickx3.service.FollowService;
import com.pickx3.service.UserService;
import com.pickx3.util.rsMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 일반 회원 가입
 */
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;
    private final FollowService followService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @Operation(summary = "회원 정보 수정", description = "test example:<br>userNum - 9<br>userImg - jpg/jpeg/png 파일<br>userIntro - test 사용자입니다. <br>userNick - 테스트유저닉네임<br>userPhone - 010-0000-0000")
    @PostMapping(path = "/user/update/{userNum}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(@PathVariable Long userNum, @ModelAttribute UserUpdateRequestDto userUpdateRequestDto) throws Exception {
        rsMessage result;

        try{
            HashMap data = new HashMap<>();
            User user = userService.update(userNum,userUpdateRequestDto);
            data.put("userNum",user.getId());
            result = new rsMessage(true, "Success" ,"200", "", data );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "회원 정보 조회" , description = "샘플 데이터 = id : 2")
    @GetMapping("/user/{userNum}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long userNum) {
        rsMessage result;
        try{
            User user = userService.searchUserById(userNum);
            result = new rsMessage(true, "Success" ,"200", "", user);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "회원 삭제" , description = "DataBase(mySql) 접속 후 삭제할 유저의 userNum 확인 후 삭제 부탁드립니다.<br>본인 아이디를 지우면서 테스트 하는 것이 낫습니다. <br>삭제 후 해당 아이디를 다시 이용하시려면 다시 로그인을 하신 후 Database에 접속하여 해당 userNum을 확인 후에 그 userNum을 사용하면 됩니다. ")
    @DeleteMapping("/user/delete/{userNum}")
    public ResponseEntity<?> delete(@PathVariable Long userNum) {
        rsMessage result;
        try{
            userService.delete(userNum);
            result = new rsMessage(true, "Success" ,"200", "", null );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "팔로우 " , description = " followerNum : 팔로우하는사람 번호 , <br>followingNum : 당하는 사람 번호")
    @PostMapping("/user/follow/")
    public ResponseEntity<?> UserFollow(@RequestBody FollowForm followForm) {
        rsMessage result;
        try{
            HashMap data = new HashMap<>();
            followService.follow(followForm);
            result = new rsMessage(true, "Success" ,"200", "팔로우 성공", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "팔로우 취소 " , description = " followerNum : 팔로우하는사람 번호 , <br>followingNum : 당하는 사람 번호")
    @PatchMapping("/user/follow/cancel")
    public ResponseEntity<?> UserFollowCancel(@RequestBody FollowForm followForm) {
        rsMessage result;
        HashMap data = new HashMap<>();
        try{
            followService.followCancel(followForm);
            result = new rsMessage(true, "Success" ,"200", "", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/follow/followList/user/{id}")
    public ResponseEntity<?> userId_list(@PathVariable Long id){
        rsMessage result;
        HashMap data = new HashMap<>();
        try{
          //  followService.findFollow(id);
            result = new rsMessage(true, "Success" ,"200", "", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
