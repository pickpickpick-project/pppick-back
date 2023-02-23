package com.pickx3.controller;

import com.pickx3.domain.entity.work_package.Payment;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentApiResponse;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentCancelDTO;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentRequestDTO;
import com.pickx3.domain.entity.work_package.dto.pay.VerifyDTO;
import com.pickx3.service.PaymentService;
import com.pickx3.util.ApiResponseMessage;
import com.pickx3.util.rsMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {
    @Autowired
    private PaymentService payService;

    @PostMapping("/verify")
    public ResponseEntity<?>  verifyPayment(String imp_uid, String merchant_uid, Model model) throws Exception {
        ApiResponseMessage result;
        HashMap data = new HashMap<>();

        VerifyDTO verifyDTO = VerifyDTO.builder()
                .imp_uid(imp_uid)
                .merchantUid(merchant_uid)
                .build();
        log.info("여기로 들어오는지" + verifyDTO.getImp_uid());
        log.info("여기로 들어오는지" + verifyDTO.getMerchantUid());

        try {
//      아임포트에서 결제 정보 받아오기 위해 토큰 받아옴
            String token = payService.getToken();

            PaymentRequestDTO paymentRequestDTO = payService.verifyPayment(verifyDTO.getImp_uid(), verifyDTO.getMerchantUid(), token);

            Payment payment = payService.payWork(paymentRequestDTO);

            data.put("payment", payment);

            result = new ApiResponseMessage(true, "Success", "200", "", data);
            return new ResponseEntity<>(result, HttpStatus.OK);

        }catch (Exception e){
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/cancel")
    public ResponseEntity<?> cancelPayment(@RequestParam("merchant_uid") String merchantUid, @RequestParam("cancel_request_amount") int cancelRequestAmount  ){
        String token = payService.getToken();
        rsMessage result;

        PaymentCancelDTO paymentCancelDTO = new PaymentCancelDTO();
        paymentCancelDTO.setCancelRequestAmount(cancelRequestAmount);
        paymentCancelDTO.setMerchantUid(merchantUid);
        try {
            PaymentApiResponse data = payService.cancelPayment(paymentCancelDTO, token);

            result = new rsMessage(true, "Success", "200", "", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new rsMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/status/")
    public ResponseEntity<?> updatePaymentStatus(@RequestParam("merchantUid") String merchantUid, @RequestParam("orderStatus") String orderStatus){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            payService.updatePaymentStatus(merchantUid, orderStatus);

            log.info("주문 번호" +  merchantUid);
            log.info("주문 상태" +  orderStatus);
            result = new ApiResponseMessage(true, "Success", "200", "", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
    /*
    * 상품 결제
    * */
//    @Operation(summary = "상품 결제", description = "회원은 상품을 결제할 수 있다")
//    @PostMapping
//    public ResponseEntity<?> payWork(PaymentRequestDTO paymentRequestDTO){
//        ApiResponseMessage result;
//        HashMap data = new HashMap<>();
//        try{
//            payService.payWork(paymentRequestDTO);
////            data.put("payInfo",payForm);
//            result = new ApiResponseMessage(true, "Success", "200", "",data);
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        } catch (Exception e) {
//            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
//            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/{userNum}")
    public ResponseEntity<?> getPaymentHistory(@PathVariable("userNum") Long userNum){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
//            data.put("data",payService.getPaymentHistory(userNum));
//            data.put("payInfo",payForm);
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }


}
