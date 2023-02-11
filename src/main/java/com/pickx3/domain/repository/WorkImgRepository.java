package com.pickx3.domain.repository;

import com.pickx3.domain.entity.work_package.WorkImg;
import com.pickx3.domain.entity.work_package.dto.work.WorkImgForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkImgRepository extends JpaRepository<WorkImg, Long> {

    List<WorkImgForm> findByWork_workNum(Long workNum);
}
