package com.teacher.domain.dto;


import lombok.Data;


@Data
public class TeacherCreateRequest {
    private Long userId;
    private String username;
    private String password;
    private String name;
    private String department;
}
