package com.pickx3.service;


import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.dto.PortfolioRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Transactional
    public Portfolio savePf(PortfolioRequestDto pfDto){

        return portfolioRepository.save(pfDto.toEntity());
    }


}
