package com.chl.bookmanager.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 全局异常
 * @author:Administrator
 * @date:2018/12/24/024
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String e404(){
        System.out.println("404");
        return "error/404";
    }
    @ExceptionHandler(RuntimeException.class)
    public String e500(){
        return "error/500";
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String MaxUpload(){
        return "error/maxUpload";
    }
}
