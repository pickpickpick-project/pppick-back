package com.pickx3.controller;

import com.pickx3.dto.PortfolioRequestDto;
import com.pickx3.service.PortfolioService;
import com.pickx3.util.ApiResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PortfolioController {
    private final PortfolioService portfolioService;

    /**
     * 포폴 등록
     * @param pfDto
     * @return
     */
    @PostMapping("/portfolio/save")
    public ResponseEntity<?> savePf(@RequestBody PortfolioRequestDto pfDto) {
        HashMap data = new HashMap<>();
        /*
            userNum
         */


        data.put("Portfolio_id", portfolioService.savePf(pfDto));

        return getResponseEntity(data);
    }

    /**
     * 포폴 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/portfolio/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws IllegalAccessException {
        HashMap data = new HashMap<>();

        portfolioService.delete(id);

        return getResponseEntity(data);
    }

    /**
     * 포폴 조회
     * @param id
     * @return
     * @throws IllegalAccessException
     */
    @GetMapping("/portfolio/{id}")
    public ResponseEntity<?> read(@PathVariable long id) throws IllegalAccessException {
        HashMap data = new HashMap();
        data.put("read", portfolioService.read(id) );


        return getResponseEntity(data);
    }

    /**
     * 포폴 목록 전체 조회
     * @return
     */
    @GetMapping("/portfolio/list")
    public ResponseEntity<?> list(){
        HashMap data = new HashMap();

        data.put("list", portfolioService.list());

        return getResponseEntity(data);
    }

















    private ResponseEntity<?> getResponseEntity(HashMap data) {
        ApiResponseMessage result;
        try{
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}

