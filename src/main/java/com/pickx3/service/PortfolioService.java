package com.pickx3.service;


import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import com.pickx3.domain.entity.portfolio_package.Tag;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.domain.repository.TagRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.dto.PortfolioRequestDto;
import com.pickx3.dto.TagRequestDto;
import com.pickx3.exception.BadRequestException;
import com.pickx3.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;


@Slf4j
@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    //저장
    @Transactional
    public Portfolio savePf(PortfolioRequestDto pfDto){

        Portfolio portfolio = portfolioRepository.save(pfDto.toEntity());

        return portfolio;
    }

    //삭제
    public void delete(long id){
        Portfolio portfolio = portfolioRepository.findById(id).get();

        portfolioRepository.deleteById(id);
    }
}
