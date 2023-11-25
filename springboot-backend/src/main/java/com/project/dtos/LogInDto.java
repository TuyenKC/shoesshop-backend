package com.project.dtos;

import lombok.Data;

@Data
public class LogInDto {
    private String username;
    private String password;

    public LogInDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
