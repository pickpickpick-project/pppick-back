package com.pickx3.controller;

import com.pickx3.domain.entity.work_package.dto.pay.PayForm;
import com.pickx3.service.PayService;
import com.pickx3.util.ApiResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

//@Tag(name = "상품 CRUD", description = "상품 관련 API")
@Slf4j
@RequestMapping("/pay")
@RestController
public class PayController {
    @Autowired
    private PayService payService;

    /*
    * 상품 결제
    * */
    @Operation(summary = "상품 결제", description = "회원은 상품을 결제할 수 있다")
    @PostMapping
    public ResponseEntity<?> payWork(PayForm payForm){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            payService.payWork(payForm);
//            data.put("payInfo",payForm);
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
