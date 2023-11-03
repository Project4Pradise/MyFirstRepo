package com.springboot.controller.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

@Data
public class UserDto {
    private  String username;
    private  String password;
    private String nickname;
    @Alias("avatar_Url")
    private String avatar;
}
