package com.pickx3.service;


import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.work_package.Orders;
import com.pickx3.domain.entity.work_package.Payment;
import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.dto.orders.OrderDetailDTO;
import com.pickx3.domain.entity.work_package.dto.orders.OrderStatus;
import com.pickx3.domain.entity.work_package.dto.orders.OrdersRequestDTO;
import com.pickx3.domain.entity.work_package.dto.orders.OrdersResponseDTO;
import com.pickx3.domain.repository.OrdersRepository;
import com.pickx3.domain.repository.PaymentRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.WorkRepository;
import com.pickx3.util.UUIDGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Slf4j
@Service
public class OrdersService {
    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkRepository workRepository;


    public Orders addOrders(OrdersRequestDTO ordersRequestDTO) {
        String uuid = UUIDGenerateUtil.makeShortUUID();
        User user = userRepository.findById(ordersRequestDTO.getUserNum()).get();
        Work work = workRepository.findById(ordersRequestDTO.getWorkNum()).get();

        Orders orders = Orders.builder()
                .merchantUid(uuid)
                .orderCount(ordersRequestDTO.getOrderCount())
                .orderPrice(ordersRequestDTO.getOrderPrice())
                .orderDate(LocalDateTime.now())
                .user(user)
                .work(work)
                .build();

        Orders order = orderRepository.save(orders);

        return order;
    }

    public List<OrdersResponseDTO> getOrdersHistory(Long userNum){
        List<Orders> orders = orderRepository.findByUser_idOrderByOrderNumDesc(userNum);
        List<OrdersResponseDTO> resOrders = new ArrayList<>();

        for(Orders order : orders){
            OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO();

            ordersResponseDTO = ordersResponseDTO.builder()
                    .orderNum(order.getOrderNum())
                    .orderCount(order.getOrderCount())
                    .orderPrice(order.getOrderPrice())
                    .orderStatus(order.getOrderStatus())
                    .orderDate(order.getOrderDate())
                    .merchantUid(order.getMerchantUid())
                    .workName(order.getWork().getWorkName())
                    .workPrice(order.getWork().getWorkPrice())
                    .build();

            System.out.println();
            resOrders.add(ordersResponseDTO);
        }
        return resOrders;
    }

    public OrderDetailDTO getOrdersDetail(Long orderNum){

        Orders orders = orderRepository.findById(orderNum).get();
        Long workId = orders.getWork().getWorkNum();
        Work work = workRepository.findById(workId).get();
        String merchantUid = orders.getMerchantUid();
        Payment payment = paymentRepository.findByMerchantUid(merchantUid);

        OrderDetailDTO dto = OrderDetailDTO
                .builder()
                .orderNum(orders.getOrderNum())
                .orderCount(orders.getOrderCount())
                .orderStatus(orders.getOrderStatus())
                .merchantUid(payment.getMerchantUid())
                .workName(work.getWorkName())
                .workPrice(work.getWorkPrice())
                .paymentStatus(payment.getPaymentStatus())
                .paymentDate(payment.getPaymentDate())
                .build();

        return dto;
    }

    public void updateOrderStatus(String merchantUid, String orderStatus){
        Orders orders = orderRepository.findByMerchantUid(merchantUid);

        OrderStatus status = OrderStatus.valueOf(orderStatus);

        log.info("주문상태" + status);
        orders.updateStatus(status);
    }
}
