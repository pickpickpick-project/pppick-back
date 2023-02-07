package com.pickx3.service;


import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.domain.repository.TagRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.dto.PortfolioRequestDto;
import com.pickx3.dto.PortfolioResponseDto;
import com.pickx3.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    //저장
    @Transactional
    public Long savePf(PortfolioRequestDto pfDto){

       Portfolio portfolio = pfDto.toEntity();
       portfolioRepository.save(portfolio);


       return portfolio.getId();

    }

/*
    @Transactional
    public Long savePf(PortfolioRequestDto pfDto, @AuthenticationPrincipal User user){

        User finduser = userRepository.findById(user.getId()).orElseThrow(() ->
                new IllegalArgumentException("id가 존재하지 않습니다." ));

        pfDto.setUserNum(finduser);

        Portfolio portfolio = Portfolio.builder()
                .portfolioName(pfDto.getPortfolioName())
                .portfolioType(pfDto.getPortfolioType())
                .portfolioDate(pfDto.getPortfolioDate())
                .user(pfDto.getUserNum())
                .build();


        portfolioRepository.save(portfolio);


        return portfolio.getId();


    }

*/

    //삭제
    public void delete(long id) throws IllegalAccessException {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(() -> new IllegalAccessException("포트폴리오가 존재하지 않음 id ="+id));
        portfolioRepository.delete(portfolio);
    }

    //조회
    public PortfolioResponseDto read(long id) throws IllegalAccessException {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(() -> new IllegalAccessException("포트폴리오 찾을 수 없음 id =" + id));

        return new PortfolioResponseDto(portfolio);

    }

    //전체 조회
    public List<PortfolioResponseDto> list(){
        List<Portfolio> portfolio = portfolioRepository.findAll();
        List<PortfolioResponseDto> portfolioResponseDtos = new ArrayList<>();
        portfolio.forEach(s -> portfolioResponseDtos.add(new PortfolioResponseDto(s)));

        return portfolioResponseDtos;
    }
}
