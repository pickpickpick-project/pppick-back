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

}
