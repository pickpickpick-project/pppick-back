package com.pickx3.controller;

import com.pickx3.domain.entity.user_package.AuthProvider;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.exception.BadRequestException;
import com.pickx3.security.token.TokenProvider;
import com.pickx3.security.token.jwt_payload_dto.ApiResponse;
import com.pickx3.security.token.jwt_payload_dto.LoginRequest;
import com.pickx3.security.token.jwt_payload_dto.SignUpRequest;
import com.pickx3.util.ApiResponseMessage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@Api(tags = {"회원가입/로그인 관련 Controller"})
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    /**
     * SNS 로그인
     *
     * @param loginRequest
     * @return
     */

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        ApiResponseMessage result = null;
//        result = new ApiResponseMessage(true, "Success", "", "");
        ResponseEntity re = new ResponseEntity<>(result, HttpStatus.OK);
        return re;
    }

    /**
     * SNS 회원 가입
     *
     * @param signUpRequest
     * @return
     */

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("이미 해당 이메일을 사용하고 있습니다.");
        }

        // 계정 생성
        User result = userRepository.save(User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .provider(AuthProvider.local)
                .build()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "성공적으로 계정 생성이 되었습니다."));
    }

    /**
     * 클라이언트 전달 컨트롤러
     *
     * @param token
     * @return [ResponseMessage, jwt, id]
     */
    @GetMapping("/token")
    public ResponseEntity<?> token(@RequestParam String token) {
        ApiResponseMessage result = null;
        HashMap data = new HashMap<>();

        try {
            Long memberId = tokenProvider.getUserIdFromToken(token);
            data.put("jwt", token);
            data.put("userNum", memberId);
            result = new ApiResponseMessage(true, "Success", "200", "", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}