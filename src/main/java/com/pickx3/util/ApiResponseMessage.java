package com.pickx3.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiResponseMessage {
    private Boolean result;
    private String msg;
    private String code;
    private String errorMsg;
    private HashMap data;

    public ApiResponseMessage(Boolean result, String msg, String errorCode, String errorMsg) {
        this.result = result;
        this.msg = msg;
        this.code = errorCode;
        this.errorMsg = errorMsg;
    }

}
