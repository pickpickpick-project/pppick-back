package com.pickx3.service;

import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User searchUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
