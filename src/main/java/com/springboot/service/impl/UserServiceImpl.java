package com.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.Constants;
import com.springboot.controller.dto.UserDto;
import com.springboot.entity.Menu;
import com.springboot.entity.User;
import com.springboot.exception.ServiceExpection;
import com.springboot.mapper.RoleMapper;
import com.springboot.mapper.RoleMenuMapper;
import com.springboot.mapper.UserMapper;
import com.springboot.service.IMenuService;
import com.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.utils.TokenUtils;
import org.apache.poi.poifs.property.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Override
    public UserDto login(UserDto userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            one.setIslogin(1);
            userMapper.updateById(one);
            BeanUtil.copyProperties(one, userDTO, true);
            String token= TokenUtils.genToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(token);
            String role=one.getRole();
            //设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
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

    @Override
    public Page fingPage(Page<User> page, String username, String email, String address) {

        return userMapper.findPage(page,username,email,address);
    }

    //获取当前角色的菜单列表
    private List<Menu> getRoleMenus(String roleFlag){
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        //当前角色的所有菜单id
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        //查出系统所有菜单
        List<Menu> menus = menuService.findMenus("");
        //new一个最后筛选完成的list
        List<Menu> roleMenus=new ArrayList<>();
        //筛选当前用户菜单
        for (Menu menu : menus) {
            if(menuIds.contains(menu.getId())){
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            children.removeIf(child->!menuIds.contains(child.getId()));

        }
        return  roleMenus;
    }


}

