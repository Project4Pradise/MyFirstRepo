package com.springboot.controller.dto;

import cn.hutool.core.annotation.Alias;
import com.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private  String username;
    private  String password;
    private String nickname;
    @Alias("avatar_Url")
    private String avatar;
    private String token;
    private String role;
    private List<Menu> menus;
}
