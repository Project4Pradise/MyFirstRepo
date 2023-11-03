package com.springboot.exception;

import lombok.Getter;

@Getter
public class ServiceExpection extends RuntimeException{
    private String code;
    public ServiceExpection(String code,String msg){
        super(msg);
        this.code=code;
    }
}
