package com.music.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class GlobalController {

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(Exception.class)
    public String handleRuntimeException(RuntimeException e) {
        log.error("拦截未知的运行时异常",e);
        return e.getMessage();
    }

}
