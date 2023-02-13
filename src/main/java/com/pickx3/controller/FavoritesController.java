package com.pickx3.controller;

import com.pickx3.dto.FavoritesDto;
import com.pickx3.domain.entity.portfolio_package.dto.PortfolioResponseDto;
import com.pickx3.service.FavoritesService;
import com.pickx3.util.rsMessage;
import io.swagger.v3.oas.annotations.Operation;
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
public class FavoritesController {

    private final FavoritesService favoritesService;

    /**
     * 좋아요
     * @param favoritesDto
     * @return
     */
    @Operation(summary = "좋아요", description = "{user : id }, {portfolio : id} 값 필요")
    @PostMapping("/portfolio/favorites/add")
    public ResponseEntity<?> addLike(@RequestBody FavoritesDto favoritesDto){
        HashMap data = new HashMap<>();
        favoritesService.addLike(favoritesDto);

        return getResponseEntity(data);
    }

    /**
     * 좋아요 취소
     * @param favoritesDto
     * @return
     */
    @Operation(summary = "좋아요 취소", description = "좋아요 누른 {user : id }, {portfolio : id} 값 필요")
    @PatchMapping("/portfolio/favorites/cancelLike")
    public ResponseEntity<?> cancelLike(@RequestBody FavoritesDto favoritesDto){
        HashMap data = new HashMap<>();
        favoritesService.cancelLike(favoritesDto);

        return getResponseEntity(data);
    }

    /**
     * 좋아요한 작업물 '목록' 보기
     * @param id
     * @return
     */
    @Operation(summary = "회원이 누른 좋아요 포폴 목록 전체 조회", description = "좋아요 누른 회원 Id 필요")
    @GetMapping("/portfolio/favorites/{id}")
    public ResponseEntity<?> select(@PathVariable Long id){

        List<PortfolioResponseDto> data = favoritesService.select(id);
        return getResponseEntity(data);
    }

    /**
     * 에러 메세지 호출
     * @param data
     * @return
     */
    private ResponseEntity<?> getResponseEntity(Object data) {
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
