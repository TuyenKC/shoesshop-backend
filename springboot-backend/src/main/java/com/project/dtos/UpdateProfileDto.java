package com.project.dtos;

import lombok.Data;

@Data
public class UpdateProfileDto {
    private String email;
    private String phone;
    private String address;
    private String fullname;
}
