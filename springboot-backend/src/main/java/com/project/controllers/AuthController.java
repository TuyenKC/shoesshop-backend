package com.project.controllers;


import com.project.dtos.*;
import com.project.exceptions.AppException;
import com.project.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UsersService usersService;
    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        try{
            usersService.signUpUser(registerDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Đăng ký thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody LogInDto logInDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Đăng nhập thành công",usersService.signInUser(logInDto)));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{username}/changepassword")
    public ResponseEntity<?> changePassword(@PathVariable String username,@RequestBody ChangePasswordDto changePasswordDto) {
        try{
            usersService.changePassword(username,changePasswordDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thay đổi mật khẩu thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }

}
