package com.teacher.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


public class LoginResponse {
    private String token;
    private String role;

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public LoginResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
// getter and setter
}
