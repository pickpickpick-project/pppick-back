package com.pickx3.service;

import com.pickx3.domain.entity.portfolio_package.PortfolioTag;
import com.pickx3.domain.entity.portfolio_package.Tag;
import com.pickx3.domain.entity.portfolio_package.dto.PortfolioResponseDto;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.domain.repository.TagRepository;
import com.pickx3.domain.repository.UserRepository;
import com.pickx3.domain.repository.PortfolioTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j @Transactional
@RequiredArgsConstructor
@Service
public class TagService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioTagRepository portfolioTagRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    public List<PortfolioResponseDto> portfolioSearchByTags(String tags) {
        String[] tagArray = tags.split(" ");
        List<Long> tagNumList = new ArrayList<>();
        List<Long> pofoNumList = new ArrayList<>();

        for(String t : tagArray){
            List<Tag> temp = tagRepository.findByTagName(t);
            for(Tag t1 : temp){
                tagNumList.add(t1.getTagNum());
            }
        }

        for(Long t :tagNumList){
            List<PortfolioTag> pf = portfolioTagRepository.findByTag_tagNum(t);
            for(PortfolioTag p : pf){
                pofoNumList.add(p.getPortfolio().getPortfolioNum());
            }
        }

        List<Long> newList = pofoNumList.stream().distinct().collect(Collectors.toList());
        List<PortfolioResponseDto> portfolioResponseDtos = new ArrayList<>();
        for(Long l : newList){
            portfolioResponseDtos.add(new PortfolioResponseDto(portfolioRepository.findById(l).get()));
        }

        return portfolioResponseDtos;
    }



}