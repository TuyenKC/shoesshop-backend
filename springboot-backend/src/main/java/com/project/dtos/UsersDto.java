package com.project.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entities.Cart;
import com.project.entities.ERole;
import com.project.entities.Images;
import com.project.entities.Orders;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Data
public class UsersDto {
    private String id;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String fullname;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date modifiedAt;
    private String role;
    private boolean status;
}
