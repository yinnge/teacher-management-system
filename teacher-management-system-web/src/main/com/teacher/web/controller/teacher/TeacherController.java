package com.teacher.web.controller.teacher;

import com.teacher.common.result.Result;
import com.teacher.domain.dto.CourseUpdateRequest;
import com.teacher.domain.dto.TeacherCreateRequest;
import com.teacher.domain.dto.TeacherUpdateRequest;
import com.teacher.domain.entity.Course;
import com.teacher.domain.entity.Teacher;
import com.teacher.domain.entity.User;
import com.teacher.service.CourseService;
import com.teacher.service.TeacherService;
import com.teacher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final UserService userService;
    private final CourseService courseService;



    @GetMapping
    public Result<List<Teacher>> list() {
        List<Teacher> teachers = teacherService.list();
        List<Teacher> dtos = teachers.stream().map(t -> {
            Teacher dto = new Teacher();
            dto.setId(t.getId());
            dto.setName(t.getName());
            dto.setDepartment(t.getDepartment());
            dto.setUserId(t.getUserId());

            // 查询关联用户
            User user = userService.getById(t.getUserId());
            dto.setUsername(user != null ? user.getUsername() : null);

            return dto;
        }).toList();

        return Result.ok(dtos);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> create(@RequestBody TeacherCreateRequest req) {
        teacherService.create(req);
        return Result.ok("教师创建成功");
    }

    @PutMapping("/{id}")

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public Result<String> update(@PathVariable Long id, @RequestBody TeacherUpdateRequest req) {
        Teacher teacher = teacherService.getById(id);
        if (teacher == null) {
            return Result.error("教师不存在或已被删除");
        }

        teacherService.updateTeacher(id, req);
        return Result.ok("修改成功");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> delete(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        if (teacher == null) {
            return Result.error("教师不存在");
        }
        teacherService.removeById(id);

        return Result.ok("删除成功");
    }
}
