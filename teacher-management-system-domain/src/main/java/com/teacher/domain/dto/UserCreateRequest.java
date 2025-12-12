package com.teacher.domain.dto;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String password;
    private String role; // ADMIN / TEACHER
}
