package com.teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.dao.mapper.UserMapper;
import com.teacher.domain.dto.UserCreateRequest;
import com.teacher.domain.dto.UserUpdateRequest;
import com.teacher.domain.entity.Teacher;
import com.teacher.domain.entity.User;
import com.teacher.service.TeacherService;
import com.teacher.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// UserServiceImpl.java (实现保持不变，但确保与接口匹配)
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }

    @Override

    public User createUser(UserCreateRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setRole(req.getRole());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setCreateTime(java.time.LocalDateTime.now());
        save(user);

        return user;
    }

    @Override
    public Optional<User> loadByUsername(String username) { // ✅ 与接口匹配
        return Optional.ofNullable(
                baseMapper.selectOne(new QueryWrapper<User>().eq("username", username))
        );
    }

    @Override
    public void updateUser(Long id, UserUpdateRequest req) {
        User user = getById(id);
        if (user == null) throw new RuntimeException("用户不存在: " + id);

        if (req.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        if (req.getRole() != null) {
            user.setRole(req.getRole());
        }
        updateById(user);
    }
}