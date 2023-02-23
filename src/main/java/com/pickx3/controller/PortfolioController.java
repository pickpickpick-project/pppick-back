package com.pickx3.controller;

import com.pickx3.domain.entity.portfolio_package.Portfolio;
import com.pickx3.domain.entity.portfolio_package.PortfolioForm;
import com.pickx3.domain.entity.portfolio_package.PortfolioImg;
import com.pickx3.domain.entity.portfolio_package.dto.PortfolioResponseDto;
import com.pickx3.domain.entity.portfolio_package.dto.TagRequestDto;
import com.pickx3.domain.repository.PortfolioRepository;
import com.pickx3.service.PortfolioImgService;
import com.pickx3.service.PortfolioService;
import com.pickx3.service.TagService;
import com.pickx3.util.rsMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PortfolioController {
    private final PortfolioService portfolioService;
    private final PortfolioImgService portfolioImgService;

    private final PortfolioRepository portfolioRepository;
    private final TagService tagService;


    /**
     * 포폴 + 포폴 이미지 + 포폴 태그 등록
     * @param portfolioForm
     * @return
     */
    @Operation(summary = "포폴 등록", description = "샘플 데이터 : <br>{ <br>&nbsp; &nbsp; files: 확장자가 jpg,png인 파일,<br>"+
            "&nbsp; &nbsp; portfolioDate : 공백으로 보내주셔도 됩니다, <br>" +
            "&nbsp; &nbsp; portfolioName : 김대박의 포트폴리오 입니다 , <br>" +
            "&nbsp; &nbsp; portfolioType: 1, <br>" +
            "&nbsp; &nbsp; TagName: #태그 #태그1 #태그2 #태그3 (중복, 공백 처리해서 저장) <br>" +
            "&nbsp; &nbsp; userNum :2 <br>}")
    @PostMapping(path = "/portfolio/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> savePf(@ModelAttribute PortfolioForm portfolioForm, TagRequestDto tagDto) throws Exception {
        HashMap data = new HashMap<>();
        //포폴
        Portfolio portfolio = portfolioService.createPf(portfolioForm,tagDto);
        //이미지
        List<MultipartFile> files = portfolioForm.getFiles();
        List<PortfolioImg> portfolioImgs = portfolioImgService.uploadPortfolioImg(files, portfolio);
        //태그
        String[] arr = tagDto.getTagName().replaceAll("\\s", "").split("#");
        Set<String> tagSet = new HashSet<>(Arrays.asList(arr));

        data.put("Portfolio", portfolio);
        data.put("PortImges", portfolioImgs);
        data.put("tags", tagSet);

        return getResponseEntity(data);
    }

    /**
     * 포폴 삭제
     * @param id
     * @return
     */
    @Operation(summary = "포폴 삭제", description = "delete api 이용할 경우 포트폴리오 신규 등록 시, 결과 값으로 나온 고유번호로 이용 부탁드립니다. <br> 고유번호 ex) portfolioNum")
    @DeleteMapping("/portfolio/{portfolioNm}")
    public ResponseEntity<?> delete(@PathVariable long portfolioNm) throws IllegalAccessException {
        HashMap data = new HashMap<>();
        portfolioImgService.removeWorkImages(portfolioNm);
        portfolioService.delete(portfolioNm);

        return getResponseEntity(data);
    }

    /**
     * 포폴 조회
     * @param id
     * @return
     * @throws IllegalAccessException
     */
    @Operation(summary = "포폴 단건 조회", description = "샘플 데이터 = id : 2 ")
    @GetMapping("/portfolio/{portfolioNm}")
    public ResponseEntity<?> read(@PathVariable long portfolioNm) throws IllegalAccessException {
        PortfolioResponseDto data = portfolioService.read(portfolioNm);

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
    @Operation(summary = "회원이 작성한 포폴 목록 조회" , description = "샘플 데이터 = id : 2")
    @GetMapping("/portfolio/list/user/{id}")
    public ResponseEntity<?> userId_list(@PathVariable Long id){
        List<PortfolioResponseDto> data = portfolioService.userId_list(id);

        return getResponseEntity(data);
    }

    @Operation(summary = "태그들에 따른 포폴 목록 조회" , description = "")
    @GetMapping("/portfolio/list/tags")
    public ResponseEntity<?> tag_list(String tags){
        List<PortfolioResponseDto> data = tagService.portfolioSearchByTags(tags);
//        List<Portfolio> portfolios = portfolioRepository.findAllById(pofoNum);
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

