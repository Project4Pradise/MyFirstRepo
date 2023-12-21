package com.springboot.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class CaptchaConfig {
    public static Map<String,String> CAPTURE_MAP=new HashMap<>();
}
