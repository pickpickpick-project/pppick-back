package com.pickx3.service;

import com.pickx3.domain.entity.work_package.Orders;
import com.pickx3.domain.entity.work_package.Payment;
import com.pickx3.domain.entity.work_package.dto.orders.OrderStatus;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentApiResponse;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentCancelDTO;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentRequestDTO;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentStatus;
import com.pickx3.domain.repository.OrdersRepository;
import com.pickx3.domain.repository.PaymentRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.WorkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@PropertySource("classpath:config.properties")
@Transactional
@Slf4j
@Service
public class PaymentService {
    @Autowired
    private PaymentRepository payRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Value("${imp_key}")
    private String imp_key;

    @Value("${imp_secret}")
    private String imp_secret;


    public Payment payWork(PaymentRequestDTO paymentRequestDTO){
//        User user = userRepository.findById(paymentRequestDTO.getPayerNum()).get();
//        Work work = workRepository.findById(paymentRequestDTO.getWorkNum()).get();

        Payment payment = Payment.builder()
                .paymentDate(LocalDateTime.now())
                .paymentPrice(paymentRequestDTO.getPaymentPrice())
                .payMethod(paymentRequestDTO.getPayMethod())
                .pg(paymentRequestDTO.getPg())
                .merchantUid(paymentRequestDTO.getMerchantUid())
                .paymentStatus(paymentRequestDTO.getPaymentStatus())
//                .user(user)
//                .work(work)
                .build();
        
        log.info("???????????? ?????????" + payment.toString());

        Payment result = payRepository.save(payment);

        log.info("???????????? ????????? ?????????" + payment.getPaymentNum());

        return result;
    }

    public void getPaymentHistory(Long userNum){
//        return payRepository.findByUser_id(userNum);
    }

    public String getToken(){
        String url = "https://api.iamport.kr/users/getToken";

        Map<String, String> params = new HashMap<String, String>();
        params.put("imp_key", imp_key);
        params.put("imp_secret", imp_secret);

        log.info("??????" + imp_key);
        log.info("??????" + imp_secret);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PaymentApiResponse> response = restTemplate.postForEntity(url, params, PaymentApiResponse.class);

        PaymentApiResponse apiResponse = response.getBody();

        String token = apiResponse.getResponse().get("access_token").toString();

        return token;
    }


    public PaymentRequestDTO verifyPayment(String imp_uid, String merchant_uid, String token) throws Exception {

//      ?????? ?????? ?????? ?????? API ??????
        URI uri = UriComponentsBuilder
                        .fromUriString("https://api.iamport.kr")
                        .path("/payments/"+ imp_uid)
                        .build().toUri();

        RequestEntity<Void> req = RequestEntity.get(uri).header("Authorization", token).build();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PaymentApiResponse> result = restTemplate.exchange(req, PaymentApiResponse.class);

        HashMap resData = result.getBody().getResponse();

        Orders order = ordersRepository.findByMerchantUid(merchant_uid);

        int paymentAmount = Integer.parseInt(result.getBody().getResponse().get("amount").toString());
        int orderPrice = order.getOrderPrice();

        String res_merchant_uid = resData.get("merchant_uid").toString();
        String paymethod = resData.get("pay_method").toString();
        String impUid = resData.get("imp_uid").toString();
        Long payerNum = order.getUser().getId();
        Long workNum = order.getOrderNum();

//      ?????? ????????? ?????? ????????? ?????? ?????? Payment ????????? ?????? ??????
        if(orderPrice == paymentAmount) {

            PaymentRequestDTO paymentRequestDTO =PaymentRequestDTO.builder()
                    .merchantUid(res_merchant_uid)
                    .pg("KG????????????")
                    .paymentPrice(paymentAmount)
                    .payMethod(paymethod)
                    .paymentStatus(PaymentStatus.COMPLETE)
                    .payerNum(payerNum)
                    .workNum(workNum)
                    .build();

            return paymentRequestDTO;
        }else{
            throw new IllegalStateException("?????? ????????? ????????????????????????");
        }
    }


    public Payment findPaymentByMerchantUid(String merchantUid){
        Payment payment = payRepository.findByMerchantUid(merchantUid);
        return payment;
    }
    public PaymentApiResponse cancelPayment(PaymentCancelDTO paymentDTO, String token){
        String merchantUid = paymentDTO.getMerchantUid();
        String cancelAmount = Integer.toString(paymentDTO.getCancelRequestAmount());

        log.info("?????? ?????????" + merchantUid);
        log.info("?????? ??????" +  cancelAmount);
        Payment payment = findPaymentByMerchantUid(merchantUid);

        String url = "https://api.iamport.kr/payments/cancel";
//        URI uri = UriComponentsBuilder
//                .fromUriString("https://api.iamport.kr")
//                .path("/payments/cancel")
//                .build().toUri();

//        RequestEntity<Void> req = RequestEntity.get(uri).header("Authorization", token).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        Map<String, String> params = new HashMap<String, String>();
        params.put("merchant_uid", merchantUid);
        params.put("amount", cancelAmount);
        params.put("checksum", cancelAmount);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();

        PaymentApiResponse result = restTemplate.exchange(url, HttpMethod.POST, requestEntity, PaymentApiResponse.class).getBody();
//        ResponseEntity<PaymentApiResponse> result = restTemplate.exchange(req, PaymentApiResponse.class);

        log.info("?????? ??????" +  result.toString());

        return result;

    }

    public void updatePaymentStatus(String merchantUid, String paymentStatus){
        Payment payment = payRepository.findByMerchantUid(merchantUid);

        PaymentStatus status = PaymentStatus.valueOf(paymentStatus);

        log.info("????????????" + status);
        payment.updateStatus(status);
    }
}
