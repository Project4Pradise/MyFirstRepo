package com.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.entity.Course;
import com.springboot.mapper.CourseMapper;
import com.springboot.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pecho
 * @since 2023-11-24
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Resource
    private CourseMapper courseMapper;
    @Override
    public Page<Course> findPage(Page<Course> page, String name) {
        return courseMapper.findPage(page,name);
    }
}