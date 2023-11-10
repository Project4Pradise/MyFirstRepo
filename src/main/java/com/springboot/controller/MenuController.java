package com.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.Result;
import com.springboot.entity.Menu;
import com.springboot.service.IMenuService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pecho
 * @since 2023-11-10
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
@Resource
private IMenuService menuService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(menuService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(menuService.removeByIds(ids));
        }

@GetMapping
public Result findAll(@RequestParam(defaultValue = "") String name) {
    QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name",name);
       List<Menu> list= menuService.list();
       //找出pid为null的一级菜单
      List<Menu> parentNode= list.stream().filter(menu -> menu.getPid()==null).collect(Collectors.toList());
      //找出一级菜单的子菜单
        for (Menu menu : parentNode) {
            //筛选所有数据中pid=父级id的数据就是二级菜单
          menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return Result.success(parentNode);
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam String name,
        @RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

}

