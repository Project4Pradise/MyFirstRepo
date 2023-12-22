package com.springboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pecho
 * @since 2023-11-24
 */
public interface CourseMapper extends BaseMapper<Course> {

    Page<Course> findPage(Page<Course> page, @Param("name") String name, @Param(Constants.WRAPPER) QueryWrapper<Course> queryWrapper);


    void setStudentCourse(@Param("courseId") Integer courseId,@Param("studentId") Integer studentId);
    void deleteStudentCourse(@Param("courseId") Integer courseId,@Param("studentId") Integer studentId);
}
