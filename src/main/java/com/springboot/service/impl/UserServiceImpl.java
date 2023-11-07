package com.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.common.Constants;
import com.springboot.controller.dto.UserDto;
import com.springboot.entity.User;
import com.springboot.exception.ServiceExpection;
import com.springboot.mapper.UserMapper;
import com.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pecho
 * @since 2023-10-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log LOG = Log.get();
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto login(UserDto userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            one.setIslogin(1);
            userMapper.updateById(one);
            BeanUtil.copyProperties(one, userDTO, true);
            String token= TokenUtils.genToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        } else {
            throw new ServiceExpection(Constants.CODE_600, "用户名或密码错误");
        }
    }
    @Override
    public User register(UserDto userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one);  // 把 copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceExpection(Constants.CODE_600, "用户已存在");
        }
        return one;
    }
    private User getUserInfo(UserDto userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new  ServiceExpection(Constants.CODE_500,"系统错误");
        }
        return one;
    }
    public UserDto logout(UserDto userDto){
        User one=getUserInfo(userDto);
        if(one!=null){
            one.setIslogin(0);
            userMapper.updateById(one);
        }
        return userDto;
    }


}

