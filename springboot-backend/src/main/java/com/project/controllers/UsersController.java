package com.project.controllers;

import com.project.dtos.*;
import com.project.exceptions.AppException;
import com.project.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @GetMapping("")
    public ResponseEntity<?> getAllUsers(){
        try{
            List<UsersDto> usersDtoList = usersService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách người dùng thành công", usersDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUsersByUsername(@PathVariable String username){
        try{
            UsersDto usersDto = usersService.findByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy thông tin người dùng thành công",usersDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable String id){
        try{
            UsersDto usersDto = usersService.getUsersById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy thông tin người dùng thành công",usersDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }

    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @PostMapping("")
    public ResponseEntity<?> addUsers(@RequestBody AddUsersDto addUsersDto){
        try{
            usersService.addUsers(addUsersDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm người dùng thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsers(@PathVariable String id,@RequestBody UpdateUsersDto updateUsersDto){
        try{
            usersService.updateUsers(id, updateUsersDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật người dùng thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getProfileByUsername(@PathVariable String username){
        try{
            ProfileDto profileDto = usersService.getProfileByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy thông tin người dùng thành công",profileDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}/updateprofile")
    public ResponseEntity<?> updateProfile(@PathVariable String id,@RequestBody UpdateProfileDto updateProfileDto) {
        try{
            usersService.updateProfile(id,updateProfileDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật thông tin thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable String id) {
        try {
            usersService.deleteUsers(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa người dùng thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}
