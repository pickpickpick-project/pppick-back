package com.pickx3.controller;

import com.pickx3.domain.entity.work_package.Work;
import com.pickx3.domain.entity.work_package.dto.WorkForm;
import com.pickx3.domain.entity.work_package.dto.WorkUpdateForm;
import com.pickx3.service.WorkService;
import com.pickx3.util.ApiResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
@Api(tags = "상품 CRUD 컨트롤러")
@RequestMapping("/works")
@RestController
public class WorkController {
    @Autowired
    private WorkService workService;

    /*
    * 상품 등록
    * */
    @ApiOperation(value = "상품 정보 저장", notes = "회원은 상품을 등록할 수 있다")
    @PostMapping
    public ResponseEntity<?> createWork(@RequestBody @Valid WorkForm workForm){
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
    @GetMapping("/users/{workerNum}")
    public ResponseEntity<?> getWorks(@PathVariable(value = "workerNum") Long workerNum){
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

   /*
    * 상품 상세정보 조회
    * */
    @ApiOperation(value = "상품 상세 정보 조회", notes = "회원은 상품 상세 정보를 조회할 수 있다")
    @GetMapping("/{workNum}")
    public ResponseEntity<?> getWorkInfo(@PathVariable(value = "workNum") Long workNum){
        ApiResponseMessage result;
        try{
            HashMap data = new HashMap();
            data.put("workInfo", workService.getWorkInfo(workNum));
            result = new ApiResponseMessage(true, "Success" ,"200", "", data );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
     }

    /*
     * 상품 정보 수정
     * */
    @ApiOperation(value = "상품 정보 수정", notes = "회원은 상품정보를 수정 할 수 있다")
    @PatchMapping
    public ResponseEntity<?> updateWork(@RequestBody @Valid WorkUpdateForm workUpdateForm){
        ApiResponseMessage result;
        try{
            HashMap data = new HashMap();
            data.put("workInfo", workService.updateWork(workUpdateForm));
            result = new ApiResponseMessage(true, "Success" ,"200", "", data );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    /*
     * 상품 정보 수정
     * */
    @ApiOperation(value = "상품 정보 삭제", notes = "회원은 상품정보를 삭제 할 수 있다")
    @DeleteMapping("/{workNum}")
    public ResponseEntity<?> removeWork(@PathVariable(value = "workNum") Long workNum){
        ApiResponseMessage result;
        try{
            workService.removeWork(workNum);
            result = new ApiResponseMessage(true, "Success" ,"200", "", null );
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new ApiResponseMessage(false, "", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
