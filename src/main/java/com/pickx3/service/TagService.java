package com.pickx3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j @Transactional
@RequiredArgsConstructor
@Service
public class TagService {

/*
    private final TagRepository tagRepository;
    private final PortfolioTagRepository portfolioTagRepository;


    public Tag createTag(Portfolio portfolio, TagRequestDto tagDto) {

        PortfolioTag portfolioTag = portfolioTagRepository.findByPortfolio_id(portfolio.getId());

        Set<Tag> tagList = new HashSet<>();
        tagList = tagDto.getTagName().split("#");
        String temp = tagDto.getTagName().split("#");
        log.debug("temp tostring ====== " + Arrays.toString(temp));



        int len = temp.length;

        for(int i=1; i<len; i++) {
            tagList.add(temp[i]);
        }

        for (Tag tag : tagList) {
            tagRepository.save(tag);
        }

        String tagString = portfolioForm.getTags();

        Tag tag = Tag.builder()
                .tagName(tagString)
                .portfolioTags(tagList)
                .build();

        tagRepository.save(tag);

        return tag;

    }

*/
}