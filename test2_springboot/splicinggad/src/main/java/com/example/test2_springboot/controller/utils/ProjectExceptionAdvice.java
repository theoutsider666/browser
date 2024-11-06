package com.example.test2_springboot.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//作为springmvc的异常处理器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public  R doException(Exception ex){
        ex.printStackTrace();
        return new R("服务器故障，请稍后再试");
    }
}
