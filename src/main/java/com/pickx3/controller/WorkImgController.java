package com.pickx3.controller;

import com.pickx3.domain.entity.work_package.dto.WorkImgForm;
import com.pickx3.service.WorkImgService;
import com.pickx3.util.ApiResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
//@CrossOrigin
//@Tag(name = "상품 이미지 CRUD", description = "상품 이미지 API")
@RequestMapping("/works/img")
@RestController
public class WorkImgController {
    @Autowired
    private WorkImgService workImgService;

//    @Operation(summary = "상품 이미지 업로드", description = "회원은 상품 이미지를 업로드할 수 있다")
    @PostMapping(consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadWorkImg(@ModelAttribute WorkImgForm files){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();

        try{
//            workImgService.uploadWorkImg(files.getFiles());
            data.put("files", files);

            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete/{workImgNum}")
    public ResponseEntity<?> removeWorkImg(@PathVariable(name = "workImgNum") Long workImgNum){
        ApiResponseMessage result;
        HashMap data = new HashMap<>();
        try{
            workImgService.removeWorkImage(workImgNum);
            result = new ApiResponseMessage(true, "Success", "200", "",data);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result = new ApiResponseMessage(false, "Error", "400", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
