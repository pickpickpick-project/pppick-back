package com.pickx3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.pickx3.domain.dto.UserUpdateRequestDto;
import com.pickx3.domain.dto.post_package.PostResponseDto;
import com.pickx3.domain.dto.post_package.PostUpdateRequestDto;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.exception.ResourceNotFoundException;
import com.pickx3.security.CurrentUser;
import com.pickx3.security.token.TokenProvider;
import com.pickx3.security.UserPrincipal;
import com.pickx3.service.UserService;
import com.pickx3.util.rsMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

/**
 * 일반 회원 가입
 */
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

//    @PutMapping(path = "/user/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
////    @PutMapping(path = "/user")
//    public ResponseEntity<?> update(@ModelAttribute UserUpdateRequestDto userUpdateRequestDto) throws Exception {
////    public ResponseEntity<?> update(@PathVariable(name = "userNum") Long userNum, @ModelAttribute UserUpdateRequestDto userUpdateRequestDto) throws Exception {
//        rsMessage result;
//
//        try{
//            Long newUserNum = userService.update(userUpdateRequestDto);
//            User newUser = userService.searchUserById(newUserNum);
//            result = new rsMessage(true, "Success" ,"200", "", newUser );
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }catch (Exception e){
//            result = new rsMessage(false, "", "400", e.getMessage());
//            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//        }
//    }

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


}
