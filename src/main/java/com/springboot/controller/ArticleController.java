package com.springboot.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.Result;
import com.springboot.entity.Article;
import com.springboot.service.IArticleService;
import com.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pecho
 * @since 2023-12-06
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
@Resource
private IArticleService articleService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Article article) {
        if(article.getId()==null){//新增
                article.setTime(DateUtil.now());//new Date()
                article.setUser(TokenUtils.getCurrentUser().getNickname());
        }
        return Result.success(articleService.saveOrUpdate(article));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(articleService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(articleService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(articleService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(articleService.getById(id));
        }

@GetMapping("/page")
public Result findPage( @RequestParam String name,
        @RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if(StrUtil.isNotBlank(name)){
                queryWrapper.like("name",name);
        }

        return Result.success(articleService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

}

