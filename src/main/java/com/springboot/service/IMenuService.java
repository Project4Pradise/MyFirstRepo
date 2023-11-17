package com.springboot.service;

import com.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pecho
 * @since 2023-11-10
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
