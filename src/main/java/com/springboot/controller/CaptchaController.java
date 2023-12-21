package com.springboot.controller;

import com.springboot.config.CaptchaConfig;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class CaptchaController {
    @RequestMapping("/captcha")
    public void captcha(@RequestParam String key,HttpServletRequest request,HttpServletResponse response)throws Exception{
        //png类型
        SpecCaptcha captcha=new SpecCaptcha(135,33,5);
        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
        //首先要把验证码在后台保存一份，但是不能保存在session里，可以存在redis里，也可以存在后台map里
        CaptchaConfig.CAPTURE_MAP.put(key,captcha.text().toLowerCase());
        CaptchaUtil.out(captcha,request,response);
        //算数类型
        /*ArithmeticCaptcha captcha=new ArithmeticCaptcha(135,33);
        captcha.setLen(4);
        captcha.getArithmeticString();
        captcha.text();
        CaptchaUtil.out(captcha,request,response);*/
    }

}
