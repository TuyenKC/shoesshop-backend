package com.project.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "users_id")
    private String id;
    @Column(name = "username", nullable = false , unique = true)
    private String username;
    @Column(name = "password" , nullable = false)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date modifiedAt;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERole role;
    @Column(name = "status")
    private boolean status;
    @OneToMany(mappedBy = "users")
    private List<Images> imagesList;
    @OneToMany(mappedBy = "users")
    private List<Rate> rateList;
    @OneToMany(mappedBy = "users")
    private List<Orders> ordersList;

}
