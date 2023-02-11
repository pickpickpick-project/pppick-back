package com.pickx3.exception.error;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogInterceptor  implements HandlerInterceptor {

    //컨트롤러에 진입하기 전에 실행됩니다
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("---------------------------------------------- CONTROLLER START ----------------------------------------------");
        log.debug(" Request URI \t: " + request.getRequestURI());
        return true;
    }
    //컨트롤러 진입 후  View가 랜더링 되기 전에 살행
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("---------------------------------------------- CONTROLLER E N D ----------------------------------------------");
    }





}
