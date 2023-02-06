package com.pickx3.controller;


import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.Tag;
import com.pickx3.domain.repository.TagRepository;
import com.pickx3.dto.PortfolioRequestDto;
import com.pickx3.dto.TagRequestDto;
import com.pickx3.security.token.TokenProvider;
import com.pickx3.service.PortfolioService;
import com.pickx3.util.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PortfolioController {
    private final PortfolioService portfolioService;
    private final TokenProvider tokenProvider;

    /**
     * 포트폴리오 등록
     * @param pfDto
     * @return
     */
    @PostMapping("/portfolio/save")
    public ResponseEntity<?> savePf(@RequestBody PortfolioRequestDto pfDto) {
        ApiResponseMessage result = null;
        HashMap data = new HashMap<>();
        Portfolio portfolio = portfolioService.savePf(pfDto);

        try{
            data.put("portfolio_id", portfolio.getId());

            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/portfolio/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        ApiResponseMessage result = null;
        HashMap data = new HashMap<>();

        try{
            portfolioService.delete(id);
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }













}

