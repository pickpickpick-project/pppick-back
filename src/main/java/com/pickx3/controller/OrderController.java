package com.pickx3.controller;


import com.pickx3.domain.entity.work_package.Orders;
import com.pickx3.domain.entity.work_package.dto.orders.OrdersRequestDTO;
import com.pickx3.service.OrdersService;
import com.pickx3.util.ApiResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

//@Tag(name = "상품 CRUD", description = "상품 관련 API")
@Slf4j
@RequestMapping("/orders")
@Controller
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    /*
    * 상품 주문
    * */
    @Operation(summary = "상품 주문", description = "샘플 데이터 : {" +
            "merchantUid : 빈 값으로 보내주시면 됩니다!, <br> " +
            "orderCount : 2,  <br>" +
            "orderPrice : 50000, <br>" +
            "userNum : 2, <br>" +
            "workNum : 2 <br>} <br>" +
            "* merchantUid는 주문 고유 번호입니다 추후 결제 정보 검증 시 필요한 아이디 값 입니다")
    @PostMapping
    public ResponseEntity<?> addOrders(OrdersRequestDTO ordersRequestDTO){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            log.info("유저 아이디" + ordersRequestDTO.getUserNum());
            Orders orders = ordersService.addOrders(ordersRequestDTO);
            data.put("merchant_uid",orders.getMerchantUid());
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userNum}")
    public ResponseEntity<?> getOrdersHistory(@PathVariable("userNum") Long userNum){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            data.put("data",ordersService.getOrdersHistory(userNum));
//            data.put("payInfo",payForm);
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
