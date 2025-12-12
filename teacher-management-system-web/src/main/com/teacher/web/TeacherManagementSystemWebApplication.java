package com.teacher.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.teacher")
@MapperScan("com.teacher.dao.mapper")
public class TeacherManagementSystemWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeacherManagementSystemWebApplication.class, args);
    }

}
