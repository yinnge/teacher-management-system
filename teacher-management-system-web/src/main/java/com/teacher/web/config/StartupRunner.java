package com.teacher.web.config;

import com.teacher.domain.entity.User;
import com.teacher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class StartupRunner implements ApplicationRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // ✅ 使用接口

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String admin = "admin";
        if (userService.loadByUsername(admin).isEmpty()) { // ✅ 使用 Optional
            User u = new User();
            u.setUsername(admin);
            u.setPassword(passwordEncoder.encode("admin"));
            u.setRole("ADMIN");
            userService.save(u);
            System.out.println("默认管理员 admin 已创建，初始密码：admin");
        }
    }
}