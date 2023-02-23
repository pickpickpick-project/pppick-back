package com.pickx3.domain.repository;

import com.pickx3.domain.entity.portfolio_package.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

   // Set<Tag> findByPortfolioTag_id(String name);

    List<Tag> findByTagName(String tagName);


}
