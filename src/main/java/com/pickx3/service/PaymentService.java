package com.pickx3.service;

import com.pickx3.domain.entity.work_package.Orders;
import com.pickx3.domain.entity.work_package.Payment;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentApiResponse;
import com.pickx3.domain.entity.work_package.dto.pay.PaymentRequestDTO;
import com.pickx3.domain.repository.OrdersRepository;
import com.pickx3.domain.repository.PaymentRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.WorkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    public Payment payWork(PaymentRequestDTO paymentRequestDTO){
//        User user = userRepository.findById(paymentRequestDTO.getPayerNum()).get();
//        Work work = workRepository.findById(paymentRequestDTO.getWorkNum()).get();

        Payment payment = Payment.builder()
                .paymentDate(LocalDate.now())
                .paymentPrice(paymentRequestDTO.getPaymentPrice())
                .payMethod(paymentRequestDTO.getPayMethod())
                .pg(paymentRequestDTO.getPg())
                .merchantUid(paymentRequestDTO.getMerchantUid())
                .paymentStatus(paymentRequestDTO.getPaymentStatus())
//                .user(user)
//                .work(work)
                .build();
        
        log.info("페이먼트 클래스" + payment.toString());

        Payment result = payRepository.save(payment);

        log.info("페이먼트 클래스 아이디" + payment.getPaymentNum());

        return result;
    }

    public void getPaymentHistory(Long userNum){
//        return payRepository.findByUser_id(userNum);
    }

    public String getToken(){
        String url = "https://api.iamport.kr/users/getToken";

        Map<String, String> params = new HashMap<String, String>();
        params.put("imp_key", "3127507500400178");
        params.put("imp_secret", "hNUfeDDzNTiiZ1OZ5f08pMVEanvdI8g0WbfbOSKCM2VM3JM9ZMhozc2KAyXjHekEqzPfszRfCM1GwNPv");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PaymentApiResponse> response = restTemplate.postForEntity(url, params, PaymentApiResponse.class);

        PaymentApiResponse apiResponse = response.getBody();

        String token = apiResponse.getResponse().get("access_token").toString();

        return token;
    }


    public PaymentRequestDTO verifyPayment(String imp_uid, String merchant_uid, String token) throws Exception {

//      결제 정보 단일 조회 API 호출
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
        Long payerNum = order.getUser().getId();
        Long workNum = order.getOrderNum();

//      주문 금액과 결제 금액이 같을 경우 Payment 객체에 정보 저장
        if(orderPrice == paymentAmount) {

            PaymentRequestDTO paymentRequestDTO =PaymentRequestDTO.builder()
                    .merchantUid(res_merchant_uid)
                    .pg("KG이니시스")
                    .paymentPrice(paymentAmount)
                    .payMethod(paymethod)
                    .paymentStatus(1)
                    .payerNum(payerNum)
                    .workNum(workNum)
                    .build();

            return paymentRequestDTO;
        }else{
            throw new IllegalStateException("결제 정보가 일치하지않습니다");
        }
    }
}
