package com.teacher.web.controller;

import com.teacher.common.result.Result;
import com.teacher.domain.dto.LoginRequest;
import com.teacher.domain.dto.LoginResponse;
import com.teacher.domain.dto.RegisterRequest;
import com.teacher.domain.entity.User;
import com.teacher.security.jwt.JwtUtils;
import com.teacher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder; // ✅ 修改为接口
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // ✅ 使用接口而不是具体实现
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest req) {
        // ✅ 修改1: 使用 Optional 处理
        User user = userService.loadByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        // ✅ 修改2: 保持密码验证逻辑
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return Result.error("用户名或密码错误");
        }

        String token = jwtUtils.generateToken(user.getUsername());
        return Result.ok(new LoginResponse(token, user.getRole()));
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterRequest req) {
        // ✅ 修改3: 检查用户名是否存在
        if (userService.loadByUsername(req.getUsername()).isPresent()) {
            return Result.error("用户名已存在");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole() == null ? "TEACHER" : req.getRole());
        user.setCreateTime(LocalDateTime.now());

        // ✅ 修改4: 使用 MyBatis Plus 的 save 方法
        boolean saved = userService.save(user);
        if (!saved) {
            return Result.error("注册失败");
        }
        return Result.ok("注册成功");
    }
}