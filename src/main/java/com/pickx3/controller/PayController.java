package com.pickx3.controller;

import com.pickx3.domain.entity.work_package.dto.pay.PaymentForm;
import com.pickx3.service.PayService;
import com.pickx3.util.ApiResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//@Tag(name = "상품 CRUD", description = "상품 관련 API")
@Slf4j
@RequestMapping("/payment")
@RestController
public class PayController {
    @Autowired
    private PayService payService;

    /*
    * 상품 결제
    * */
    @Operation(summary = "상품 결제", description = "회원은 상품을 결제할 수 있다")
    @PostMapping
    public ResponseEntity<?> payWork(PaymentForm paymentForm){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            payService.payWork(paymentForm);
//            data.put("payInfo",payForm);
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userNum}")
    public ResponseEntity<?> getPaymentHistory(@PathVariable("userNum") Long userNum){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            data.put("data",payService.getPaymentHistory(userNum));
//            data.put("payInfo",payForm);
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
