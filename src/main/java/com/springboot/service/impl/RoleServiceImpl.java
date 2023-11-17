package com.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.entity.Menu;
import com.springboot.entity.Role;
import com.springboot.entity.RoleMenu;
import com.springboot.mapper.RoleMapper;
import com.springboot.mapper.RoleMenuMapper;
import com.springboot.service.IMenuService;
import com.springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pecho
 * @since 2023-11-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
@Resource
private RoleMenuMapper roleMenuMapper;

@Resource
private IMenuService menuService;


    @Override
    @Transactional
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
/*        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role",roleId);
        roleMenuMapper.delete(queryWrapper);*/
        //先删除当前角色id绑定的所有关系
        roleMenuMapper.deleteByRoleId(roleId);
        //再把前端传过来的菜单id绑定到当前角色id上去
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            if(menu.getPid()!=null&&!menuIds.contains(menu.getPid())){//二级菜单 并且传过来的menuid数组里面没有它的父级id
                //那么就得补上父级id
                RoleMenu roleMenu=new RoleMenu();
                roleMenu.setMenu_id(menuId);
                roleMenu.setRole_id(menu.getPid());
                roleMenuMapper.insert(roleMenu);
            }
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setMenu_id(menuId);
            roleMenu.setRole_id(roleId);
            roleMenuMapper.insert(roleMenu);
            
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }
}
