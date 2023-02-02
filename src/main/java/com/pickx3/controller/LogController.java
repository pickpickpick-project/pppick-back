package com.pickx3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 에러 페이지 확인
 */
@Controller
public class LogController {

    private final Logger logger = LoggerFactory.getLogger("LoggerController 의 로그");
/*
    @GetMapping("/log")
    public String log() {
        logger.info("로깅 발생!");
        return "로그 체크";
    }
*/
    @GetMapping("/error/400")
    public String A400() {
    logger.info("400");
    return "error/400";
}
    @GetMapping("/error/403")
    public String A403() {
        logger.info("403");
        return "error/403";
    }

    @GetMapping("/error/404")
    public String A404() {
        logger.info("404");
        return "error/404";
    }
    @GetMapping("/error/408")
    public String A408() {
        logger.info("408");
        return "error/408";
    }
    @GetMapping("/error/500")
    public String A500() {
        logger.info("500");
        return "error/500";
    }
}