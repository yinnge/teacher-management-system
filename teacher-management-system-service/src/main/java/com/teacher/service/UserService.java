package com.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.dto.UserCreateRequest;
import com.teacher.domain.dto.UserUpdateRequest;
import com.teacher.domain.entity.User;

import java.util.Optional;

public interface UserService extends IService<User> {
    Optional<User> loadByUsername(String username);
    User createUser(UserCreateRequest req);
    void updateUser(Long id, UserUpdateRequest req);
}
