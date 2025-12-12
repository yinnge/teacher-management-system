package com.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.dto.CourseCreateRequest;
import com.teacher.domain.dto.CourseUpdateRequest;
import com.teacher.domain.entity.Course;

public interface CourseService extends IService<Course> {
    void create(CourseCreateRequest req);
    void updateCourse(Long id, CourseUpdateRequest req);
}
