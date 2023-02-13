package com.pickx3.service;


import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.PortfolioForm;
import com.pickx3.domain.entity.portfolio_package.dto.PortfolioResponseDto;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.domain.repository.TagRepository;
import com.pickx3.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Slf4j
@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;

    // 저장
    @Transactional
    public Long savePf(PortfolioForm portfolioForm) throws IllegalAccessException {
        User user = userRepository.findById(portfolioForm.getUserNum()).orElseThrow(() -> new IllegalAccessException("포트폴리오가 존재하지 않음 id ="+id));

        Portfolio portfolio = Portfolio.builder()
                .portfolioName(portfolioForm.getPortfolioName())
                .portfolioType(Integer.parseInt(portfolioForm.getPortfolioType()))
                .portfolioDate(new Date())
                .user(user)
                .build();

        portfolioRepository.save(portfolio);
        ///////////

        // 태그
        //tagService.createTag(pfDto);



        return portfolio.getId();
    }

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

    //전체 목록 조회보기
    public List<PortfolioResponseDto> list(){
        List<Portfolio> portfolio = portfolioRepository.findAll();

        List<PortfolioResponseDto> portfolioResponseDtos = new ArrayList<>();
        portfolio.forEach(s -> portfolioResponseDtos.add(new PortfolioResponseDto(s)));

        return portfolioResponseDtos;
    }

    //자기가 작성한 포폴 목록 조회
    public List<PortfolioResponseDto> userId_list(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user_id가 존재하지 않습니다." ));

        List<Portfolio> portfolio = portfolioRepository.findByUser_id(user.getId());
        List<PortfolioResponseDto> portfolioResponseDtos = new ArrayList<>();
        portfolio.forEach(s -> portfolioResponseDtos.add(new PortfolioResponseDto(s)));

        return portfolioResponseDtos;
    }
    // 반환용
    public PortfolioResponseDto searchByPfId(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new PortfolioResponseDto(portfolio);
    }
}
