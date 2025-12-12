package com.teacher.web.controller;

import com.teacher.common.result.Result;
import com.teacher.domain.entity.User;
import com.teacher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.ok(userService.list());
    }

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        return Result.ok(userService.getById(id));
    }

    @PostMapping
    public Result<Boolean> add(@RequestBody User user) {
        return Result.ok(userService.save(user));
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody User user) {
        return Result.ok(userService.updateById(user));
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.ok(userService.removeById(id));
    }
}
