package com.pickx3.domain.repository;


import com.pickx3.domain.entity.work_package.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser_id(Long userId);

    Orders findByMerchantUid(String merchantUid);
}
