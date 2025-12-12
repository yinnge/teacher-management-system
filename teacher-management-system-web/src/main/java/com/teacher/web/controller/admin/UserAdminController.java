package com.teacher.web.controller.admin;

import com.teacher.common.result.Result;
import com.teacher.domain.dto.UserCreateRequest;
import com.teacher.domain.dto.UserUpdateRequest;
import com.teacher.domain.entity.User;
import com.teacher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService userService;

    /** 查询全部用户 */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<List<User>> list() {
        return Result.ok(userService.list());
    }

    /** 创建用户 */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> create(@RequestBody UserCreateRequest req) {
        userService.createUser(req);
        return Result.ok("创建成功");
    }

    /** 修改用户 */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> update(@PathVariable Long id, @RequestBody UserUpdateRequest req) {
        userService.updateUser(id, req);
        return Result.ok("修改成功");
    }

    /** 删除用户 */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<String> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.ok("删除成功");
    }
}
