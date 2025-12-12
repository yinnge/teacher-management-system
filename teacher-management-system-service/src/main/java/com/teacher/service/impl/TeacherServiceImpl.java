package com.teacher.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.dao.mapper.TeacherMapper;
import com.teacher.domain.dto.TeacherCreateRequest;
import com.teacher.domain.dto.TeacherUpdateRequest;
import com.teacher.domain.dto.UserCreateRequest;
import com.teacher.domain.entity.Teacher;
import com.teacher.domain.entity.User;
import com.teacher.service.CourseService;
import com.teacher.service.TeacherService;
import com.teacher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.teacher.domain.entity.Course;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    private final UserService userService;
    private final CourseService courseService;

    @Override
    public void create(TeacherCreateRequest req) {
        // 1. 先创建用户账号
        UserCreateRequest userReq = new UserCreateRequest();
        userReq.setUsername(req.getUsername());
        userReq.setRole("TEACHER");

        // 如果没填密码，生成一个随机密码
        String password = req.getPassword();
        if (password == null || password.trim().isEmpty()) {
            password = generateRandomPassword(); // 你可以实现这个方法
        }
        userReq.setPassword(password);

        User createdUser = userService.createUser(userReq);

        // 2. 再创建教师信息
        Teacher teacher = new Teacher();
        teacher.setUserId(createdUser.getId());
        teacher.setName(req.getName());
        teacher.setDepartment(req.getDepartment());
        save(teacher);

        System.out.println("教师创建成功！用户名: " + req.getUsername() + ", 密码: " + password);
    }

    @Override
    public void updateTeacher(Long id, TeacherUpdateRequest req) {
        Teacher t = getById(id);
        if (t == null) throw new RuntimeException("教师不存在");

        t.setName(req.getName());
        t.setDepartment(req.getDepartment());
        updateById(t);
    }

    // 可选：生成随机密码
    private String generateRandomPassword() {
        return "teacher" + System.currentTimeMillis() % 10000; // 简单示例
    }

    @Override
    @Transactional
    public boolean removeById(Serializable id) {
        // 1. 查出教师信息
        Teacher teacher = getById(id);
        if (teacher == null) {
            return false;
        }

        // 2. 解除课程关联（把课程的 teacher_id 设为 null）
        courseService.lambdaUpdate()
                .eq(Course::getTeacherId, id)
                .set(Course::getTeacherId, null)
                .update();

        // 3. 删除教师记录
        boolean teacherDeleted = super.removeById(id);

        // 4. 删除关联的用户账号
        if (teacherDeleted && teacher.getUserId() != null) {
            userService.removeById(teacher.getUserId());
        }

        System.out.println("已删除教师: " + teacher.getName());
        return teacherDeleted;
    }

}