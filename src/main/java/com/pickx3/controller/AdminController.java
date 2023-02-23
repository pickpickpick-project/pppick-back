package com.pickx3.controller;

//import com.pickx3.domain.dto.UserUpdateRequestDto;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.user_package.dto.UserLoginRequestDto;
import com.pickx3.domain.entity.work_package.Orders;
import com.pickx3.domain.entity.work_package.Payment;
import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentResponseDTO;
import com.pickx3.domain.repository.*;
import com.pickx3.exception.ResourceNotFoundException;
import com.pickx3.security.CurrentUser;
import com.pickx3.security.UserPrincipal;
import com.pickx3.security.token.TokenProvider;
import com.pickx3.service.UserService;
import com.pickx3.util.rsMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final UserRepository userRepository;
    ;

    private final PortfolioRepository portfolioRepository;

    private final WorkRepository workRepository;
    private final PaymentRepository paymentRepository;

    @Operation(summary = "로그인", description = "test example:<br>id : admin<br>password : admin ")
    @PostMapping("/admin/login")
    public ResponseEntity<?> getUserCorrect(UserLoginRequestDto userLoginRequestDto) {
        rsMessage result;
        try {
            User user = userRepository.findByEmail(userLoginRequestDto.getId()).get();

            if (user.getPassword().equals(userLoginRequestDto.getPassword())) {
                result = new rsMessage(true, "Success", "200", "로그인이 성공적으로 완료되었습니다.");
            } else {
                result = new rsMessage(true, "fail", "400", "비밀번호가 틀립니다.");

            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "회원 관리 - 회원 정보 전체 조회", description = "")
    @GetMapping("/admin/manage/user")
    public ResponseEntity<?> getUserList() {
        rsMessage result;
        try {
            List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            result = new rsMessage(true, "Success", "200", "", users);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "상품 관리 - 상품 정보 전체 조회", description = "")
    @GetMapping("/admin/manage/work")
    public ResponseEntity<?> getWorkList() {
        rsMessage result;
        try {
            List<Work> works = workRepository.findAll(Sort.by(Sort.Direction.DESC, "workNum"));
            result = new rsMessage(true, "Success", "200", "", works);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "포트폴리오 관리 - 프트폴리오 정보 전체 조회", description = "")
    @GetMapping("/admin/manage/portfolio")
    public ResponseEntity<?> getPList() {
        rsMessage result;
        try {
            List<Portfolio> portfolios = portfolioRepository.findAll(Sort.by(Sort.Direction.DESC, "portfolioNum"));
            result = new rsMessage(true, "Success", "200", "", portfolios);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "결제 관리 - 결제 정보 전체 조회", description = "")
    @GetMapping("/admin/manage/payment")
    public ResponseEntity<?> getPayList() {
        rsMessage result;
        try {
            List<Object[]> resulto = paymentRepository.findSameMerchant();
            List<PaymentResponseDTO> paymentResponseDTOS = new ArrayList<>();
            for (Object[] o : resulto) {
                Payment payment = (Payment) o[0];
                Orders orders = (Orders) o[1];
                paymentResponseDTOS.add(new PaymentResponseDTO(payment, orders));
            }
            paymentResponseDTOS = paymentResponseDTOS.stream().sorted(Comparator.comparing(PaymentResponseDTO::getPaymentId).reversed()).collect(Collectors.toList());
            result = new rsMessage(true, "Success", "200", "", paymentResponseDTOS);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new rsMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
