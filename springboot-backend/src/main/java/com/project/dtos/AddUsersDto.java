package com.project.dtos;
import lombok.Data;
@Data
public class AddUsersDto {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String fullname;
    private String role;
    private boolean status;
}
