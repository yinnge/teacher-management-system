package com.teacher.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.dao.mapper.CourseMapper;
import com.teacher.domain.dto.CourseCreateRequest;
import com.teacher.domain.dto.CourseUpdateRequest;
import com.teacher.domain.entity.Course;
import com.teacher.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Override
    public void create(CourseCreateRequest req) {
        Course c = new Course();
        c.setName(req.getName());
        c.setDescription(req.getDescription());
        c.setTeacherId(req.getTeacherId());
        save(c);
    }

    @Override
    public void updateCourse(Long id, CourseUpdateRequest req) {
        Course c = getById(id);
        // 改成返回 boolean，不抛异常
        if (c == null) {
            return; // 或者 return false;
        }

        c.setName(req.getName());
        c.setDescription(req.getDescription());
        if (req.getTeacherId() != null) {  // 防止 null
            c.setTeacherId(req.getTeacherId());
        }
        updateById(c);
    }
}
