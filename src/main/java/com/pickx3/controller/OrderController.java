package com.pickx3.controller;


import com.pickx3.domain.entity.work_package.Orders;
import com.pickx3.domain.entity.work_package.dto.orders.OrderDetailDTO;
import com.pickx3.domain.entity.work_package.dto.orders.OrdersRequestDTO;
import com.pickx3.domain.entity.work_package.dto.orders.OrdersResponseDTO;
import com.pickx3.service.OrdersService;
import com.pickx3.util.ApiResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    @Operation(summary = "주문 상세 조회", description = "샘플 데이터 = orderNum : 6 <br>" +
            "* orderNum : 주문 고유 번호(pk) <br> " +
            "* orderCount : 주문 수량 <br>" +
            "* merchantUid : 주문 번호 & 결제변호 <br> " +
            "* workName : 상품명  <br>" +
            "* workPrice : 상품 가격, <br>" +
            "* paymentPrice : 결제 금액, <br>" +
            "* payMethod : 결제수단 <br>" +
            "* payMethod : 결제상태(1 : 결제, 2 : 거래 확정, 3: 환불) <br>" +
            "* paymentDate : 결제일자")
    @GetMapping("/{orderNum}")
    public ResponseEntity<?> getOrdersDetail(@PathVariable("orderNum") Long orderNum){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            OrderDetailDTO orders = ordersService.getOrdersDetail(orderNum);
            data.put("data",orders);
//            data.put("payInfo",payForm);
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "상품 주문 목록 조회", description = "샘플 데이터 = userNum : 2<br>" +
            "* merchantUid : 주문번호 <br> " +
            "* workName : 상품명  <br>" +
            "* workPrice : 상품 가격, <br>" +
            "* orderCount : 주문 수량, <br>" +
            "* orderPrice : 주문 금액, <br>" +
            "* orderDate : 주문 날짜, <br>" +
            "* orderStatus : 주문 상태")
    @GetMapping("/user/{userNum}")
    public ResponseEntity<?> getOrdersHistory(@PathVariable("userNum") Long userNum){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            List<OrdersResponseDTO> orders = ordersService.getOrdersHistory(userNum);
            data.put("data",orders);
//            data.put("payInfo",payForm);
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/status/")
    public ResponseEntity<?> updateOrderStatus(@RequestParam("merchantUid") String merchantUid, @RequestParam("orderStatus") String orderStatus){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            ordersService.updateOrderStatus(merchantUid, orderStatus);

            log.info("주문 번호" +  merchantUid);
            log.info("주문 상태" +  orderStatus);

            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}
