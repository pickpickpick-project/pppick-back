package com.pickx3.domain.repository;

import com.pickx3.domain.entity.work_package.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {
}
