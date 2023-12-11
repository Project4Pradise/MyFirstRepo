package com.springboot.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.Result;
import com.springboot.entity.Comment;
import com.springboot.service.ICommentService;
import com.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pecho
 * @since 2023-12-11
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
@Resource
private ICommentService commentService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Comment comment) {
        if(comment.getId()==null){//表示新增评论
                comment.setUserId(TokenUtils.getCurrentUser().getId());
                comment.setTime(DateUtil.now());
        }
        return Result.success(commentService.saveOrUpdate(comment));
        }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(commentService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(commentService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(commentService.list());
        }

        @GetMapping("/tree/{articleId}")
        public Result findTree(@PathVariable Integer articleId) {

                List<Comment> articleComments = commentService.findCommnetDetail(articleId);//查询所有的评论数据
                //查询评论 （不包括回复）
                List<Comment> originList = articleComments.stream().filter(comment -> comment.getOriginId() == null).collect(Collectors.toList());
                //设置评论数据的子节点，也就是回复内容
                for (Comment origin : originList) {
                        articleComments.stream().filter(comment -> comment.getId().equals(comment.getOriginId())).collect(Collectors.toList());
                        origin.setChildren();
                }
                return Result.success(originList);
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(commentService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(commentService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

}

