package com.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.dto.TeacherCreateRequest;
import com.teacher.domain.dto.TeacherUpdateRequest;
import com.teacher.domain.entity.Teacher;

public interface TeacherService extends IService<Teacher> {

    void create(TeacherCreateRequest req);
    void updateTeacher(Long userId, TeacherUpdateRequest req);
}
