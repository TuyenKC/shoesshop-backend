package com.project.dtos;

import lombok.Data;

@Data
public class UpdateUsersDto {
    private String email;
    private String phone;
    private String address;
    private String fullname;
    private String role;
    private boolean status;
}
