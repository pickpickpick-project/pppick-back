package com.pickx3.service;


import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.entity.work_package.Orders;
import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.dto.orders.OrdersRequestDTO;
import com.pickx3.domain.repository.OrdersRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.WorkRepository;
import com.pickx3.util.UUIDGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class OrdersService {
    @Autowired
    private OrdersRepository orderRepository;

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
                .orderDate(LocalDate.now())
                .orderStatus(0)
                .user(user)
                .work(work)
                .build();

        Orders order = orderRepository.save(orders);

        return order;
    }

    public List<Orders> getOrdersHistory(Long userNum){
        return orderRepository.findByUser_id(userNum);
    }


}
