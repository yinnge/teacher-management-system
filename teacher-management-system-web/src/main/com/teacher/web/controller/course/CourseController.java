package com.teacher.web.controller.course;

import com.teacher.common.result.Result;
import com.teacher.domain.dto.CourseCreateRequest;
import com.teacher.domain.dto.CourseUpdateRequest;
import com.teacher.domain.entity.Course;
import com.teacher.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public Result<List<Course>> list() {
        return Result.ok(courseService.list());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public Result<String> create(@RequestBody CourseCreateRequest req) {
        courseService.create(req);
        return Result.ok("创建成功");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public Result<String> update(@PathVariable Long id, @RequestBody CourseUpdateRequest req) {
        try {
            courseService.updateCourse(id, req);
            return Result.ok("修改成功");
        } catch (RuntimeException e) {
            if ("课程不存在".equals(e.getMessage())) {
                return Result.error("课程不存在或已被删除");
            }
            return Result.error("更新失败：" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> delete(@PathVariable Long id) {
        courseService.removeById(id);
        return Result.ok("删除成功");
    }
}
