package com.pickx3.controller;

import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.exception.ResourceNotFoundException;
import com.pickx3.security.CurrentUser;
import com.pickx3.security.token.TokenProvider;
import com.pickx3.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 일반 회원 가입
 */
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;


    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }


}
