package com.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.controller.dto.UserDto;
import com.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pecho
 * @since 2023-10-26
 */
public interface IUserService extends IService<User> {

    UserDto login(UserDto userDto);

    User register(UserDto userDto);
    UserDto logout(UserDto userDto);

    Page<User> fingPage(Page<User> objectPage, String username, String email, String address);
}
