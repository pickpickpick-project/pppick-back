package com.pickx3.controller;

import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.dto.WorkForm;
import com.pickx3.service.WorkService;
import com.pickx3.util.ApiResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Api(tags = "상품 CRUD 컨트롤러")
@RestController("/work")
public class WorkController {
    @Autowired
    private WorkService workService;

    /*
    * 상품 등록
    * */
    @ApiOperation(value = "상품 정보 저장", notes = "회원은 상품을 등록할 수 있다")
    @PostMapping
    public ResponseEntity<?> createWork(WorkForm workForm){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            Work work = workService.createWork(workForm);
            data.put("workNum",work.getWorkNum());
            data.put("workerNum",work.getWorkNum());
            data.put("workName",work.getWorkName());
            data.put("workPrice",work.getWorkPrice());
            data.put("workDesc",work.getWorkDesc());

            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    /*
     * 회원별 상품 목록 조회
     * */
    @ApiOperation(value = "회원별 상품 목록 조회", notes = "회원은 자신이 등록한 상품 목록을 조회 할 수 있다")
    @GetMapping
    public ResponseEntity<?> getWorks(@RequestParam(value = "workerNum") Long workerNum){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            List<Work> works = workService.getWorks(workerNum);
            data.put("works",works);

            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
