package com.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import cn.hutool.core.io.unit.DataUnit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.common.Result;
import com.springboot.config.AuthAccess;
import com.springboot.entity.User;
import com.springboot.mapper.FileMapper;
import com.springboot.service.IUserService;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private IUserService userService;
    @Resource
    private FileMapper fileMapper;
    @GetMapping("/example")
    public Result get(){
        Map<String,Object> map=new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y",CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }
@GetMapping("/members")
    public Result members(){
    List<User> list = userService.list();
     int q1=0;//第一季度
     int q2=0;//第二季度
     int q3=0;//第三季度
     int q4=0;//第四季度
     for(User user:list){
         Date createTime = user.getCreateTime();
         Quarter quarter = DateUtil.quarterEnum(createTime);
         switch (quarter){
             case Q1:q1+=1;break;
             case Q2:q2+=1;break;
             case Q3:q3+=1;break;
             case Q4:q4+=1;break;
             default:break;
         }

     }
     return Result.success(CollUtil.newArrayList( q1,q2,q3,q4 ));
    }
    @AuthAccess
    @GetMapping("/file/front/all")
    public Result frontAll() {
        return Result.success(fileMapper.selectList(null ));
    }
    @GetMapping("/islogin")
    public Result loginUser(){
        List<User> list = userService.list();
        int islogin=0;
        int notlogin=0;
        for(User user:list){
            int isloginNum=user.getIslogin();
            switch (isloginNum){
                case 1:islogin+=1;break;
                case 0:notlogin+=1;break;
                default:break;
            }

        }
        return Result.success(CollUtil.newArrayList(islogin,notlogin));
    }
}
