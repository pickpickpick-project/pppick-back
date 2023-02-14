package com.pickx3.controller;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.PortfolioForm;
import com.pickx3.domain.entity.portfolio_package.PortfolioImg;
import com.pickx3.domain.entity.portfolio_package.dto.PortfolioResponseDto;
import com.pickx3.service.PortfolioImgService;
import com.pickx3.service.PortfolioService;
import com.pickx3.util.rsMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PortfolioController {
    private final PortfolioService portfolioService;
    private final PortfolioImgService portfolioImgService;

    /**
     * 포폴 등록
     * @param portfolioForm
     * @return
     */

    @Operation(summary = "포폴 등록", description = "{user : id} 값 필요")
    @PostMapping(path = "/portfolio/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> savePf(@ModelAttribute PortfolioForm portfolioForm) throws Exception {
        HashMap data = new HashMap<>();
        //data.put("Portfolio_id", portfolioService.savePf(portfolioForm));

        Portfolio portfolio = portfolioService.createPf(portfolioForm);
        log.debug(" =========== porfort id ======= ========================================== " + portfolio.getId());
        List<MultipartFile> files = portfolioForm.getFiles();
        log.debug(" =========== files ======= ========================================== " + files);

        List<PortfolioImg> portfolioImgs = portfolioImgService.uploadPortfolioImg(files, portfolio);

        data.put("Portfolio_id", portfolio.getId());
        data.put("PortImges", portfolioImgs);

        return getResponseEntity(data);
    }

    /**
     * 포폴 삭제
     * @param id
     * @return
     */
    @Operation(summary = "포폴 삭제", description = "{portfolio : id} 값 필요")
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
    @Operation(summary = "포폴 단건 조회", description = "{portfolio : id} 값 필요")
    @GetMapping("/portfolio/{id}")
    public ResponseEntity<?> read(@PathVariable long id) throws IllegalAccessException {
        PortfolioResponseDto data = portfolioService.read(id);

        return getResponseEntity(data);
    }

    /**
     * 포폴 목록 전체 조회              // 수정 필요
     * @return
     */
    @Operation(summary = "포폴 전체 목록 조회")
    @GetMapping("/portfolio/list")
    public ResponseEntity<?> list(){
        List<PortfolioResponseDto> data = portfolioService.list();

        return getResponseEntity(data);
    }

    /**
     * 특정 회원 포폴 전체 조회
     * @param id
     * @return
     */
    @Operation(summary = "회원이 작성한 포폴 목록 조회" , description = "user : id 필요")
    @GetMapping("/portfolio/list/user/{id}")
    public ResponseEntity<?> userId_list(@PathVariable Long id){
        List<PortfolioResponseDto> data = portfolioService.userId_list(id);

        return getResponseEntity(data);
    }


    /**
     * 에러 메세지 호출
     * @param data
     * @return
     */
    private ResponseEntity<?> getResponseEntity(Object data){
        rsMessage result = null;
        try{
            result = new rsMessage(true, "Success", "200", "요청에 성공 하셧습니다", data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new rsMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

}

