package com.springboot.service;

import com.springboot.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pecho
 * @since 2023-12-11
 */
public interface ICommentService extends IService<Comment> {

    List<Comment> findCommnetDetail(Integer articleId);
}
