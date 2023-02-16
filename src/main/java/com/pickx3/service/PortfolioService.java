package com.pickx3.service;


import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.PortfolioForm;
import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import com.pickx3.domain.entity.portfolio_package.Tag;
import com.pickx3.domain.entity.portfolio_package.dto.PortfolioResponseDto;
import com.pickx3.domain.entity.portfolio_package.dto.TagRequestDto;
import com.pickx3.domain.entity.user_package.User;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.domain.repository.TagRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.post_package.PortfolioTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Slf4j  @Transactional
@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioTagRepository portfolioTagRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    // 저장
    @Transactional
    public Portfolio createPf(PortfolioForm portfolioForm, TagRequestDto tagDto){
        User user = userRepository.findById(portfolioForm.getUserNum()).get();

        String[] arr = tagDto.getTagName().replaceAll("\\s", "").split("#");
        Set<String> tagSet = new HashSet<>(Arrays.asList(arr));
        log.info("tagset list = " + tagSet.size());

        //포폴 등록
        Portfolio portfolio = Portfolio.builder()
                .portfolioName(portfolioForm.getPortfolioName())
                .portfolioType(Integer.parseInt(portfolioForm.getPortfolioType()))
                .portfolioDate(new Date())
                .user(user)
                .build();

        Portfolio savePort = portfolioRepository.save(portfolio);

        //태그 등록
        Tag saveTag = null;
        for (String s : tagSet) {
            if(s.isEmpty()) continue;
            Tag a = Tag.builder().tagName(s)
                    .build();

            saveTag = tagRepository.save(a);

        }
        log.debug("save tag test ===== " + saveTag.getTagName());


        //태그+ 포폴 = 포폴태그 등록
        PortfolioTag portfolioTag = PortfolioTag.builder()
                .portfolio(savePort)
                .tag(saveTag)
                .build();

        portfolioTagRepository.save(portfolioTag);


        return portfolio;
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

}
