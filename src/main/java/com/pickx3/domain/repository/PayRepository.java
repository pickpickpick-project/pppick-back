package com.pickx3.domain.repository;

import com.pickx3.domain.entity.work_package.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Long> {
}
