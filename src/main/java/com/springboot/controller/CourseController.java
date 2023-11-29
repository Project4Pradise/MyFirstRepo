package com.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.Result;
import com.springboot.entity.Course;
import com.springboot.entity.User;
import com.springboot.service.ICourseService;
import com.springboot.service.IUserService;
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
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/course")
public class CourseController {
@Resource
private ICourseService courseService;
@Resource
private IUserService userService;

// 新增或者更新
@PostMapping
public Result save(@RequestBody Course course) {
        return Result.success(courseService.saveOrUpdate(course));
        }
        @PostMapping("/StudentCourse/{studentId}/{courseId}")
        public Result studentCourse(@PathVariable Integer studentId,@PathVariable Integer courseId) {
                courseService.setStudentCourse(studentId,courseId);
                return Result.success();
        }
@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
        return Result.success(courseService.removeById(id));
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(courseService.removeByIds(ids));
        }

@GetMapping
public Result findAll() {
        return Result.success(courseService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(courseService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam String name,
                       @RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
/*        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        List<Course> records = page.getRecords();
        for(Course record:records){
                User user=userService.getById(record.getTeacherId());
                if(user!=null){
                        record.setTeacher(user.getNickname());
                }
        }*/
        Page<Course> page = courseService.findPage(new Page<>(pageNum, pageSize), name);
        return Result.success(page);

        }

}

