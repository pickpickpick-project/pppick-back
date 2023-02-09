package com.pickx3.domain.repository;

import com.pickx3.domain.entity.user_package.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);


    Optional<User> findById(User userNum);
}
