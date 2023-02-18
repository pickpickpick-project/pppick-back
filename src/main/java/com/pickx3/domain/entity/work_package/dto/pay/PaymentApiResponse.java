package com.pickx3.domain.entity.work_package.dto.pay;

import lombok.Data;

import java.util.HashMap;

@Data


public class PaymentApiResponse {
    private int code;
    private String message;
    private HashMap  response;
}
