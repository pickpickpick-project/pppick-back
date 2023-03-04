package com.pickx3.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class rsMessage {
    private Boolean result;
    private String msg;
    private String code;
    private String errorMsg;

    private Object data;

    public rsMessage(Boolean result, String msg, String errorCode, String errorMsg) {
        this.result = result;
        this.msg = msg;
        this.code = errorCode;
        this.errorMsg = errorMsg;
    }

}
