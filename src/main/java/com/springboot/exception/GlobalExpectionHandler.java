package com.springboot.exception;


import com.springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExpectionHandler {
    @ExceptionHandler(ServiceExpection.class)
    @ResponseBody
    public Result handle(ServiceExpection se){
        return Result.error(se.getCode(),se.getMessage());
    }
}
