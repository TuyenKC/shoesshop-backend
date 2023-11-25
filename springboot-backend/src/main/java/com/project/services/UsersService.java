package com.project.services;

import com.project.dtos.*;
import com.project.entities.Users;

import java.util.List;

public interface UsersService {
    public Users findById(String id);
    public UsersDto findByUsername(String username);
    public void signUpUser(RegisterDto registerDto);
    public JwtResponse signInUser(LogInDto logInDto);
    public void changePassword(String id, ChangePasswordDto changePasswordDto);
    public ProfileDto getProfileByUsername(String username);
    public void updateProfile(String id, UpdateProfileDto updateProfileDto);
    public UsersDto addUsers(AddUsersDto addUsersDto);
    public UsersDto getUsersById(String id);
    public List<UsersDto> getAllUsers();
    public void deleteUsers(String id);
    public UsersDto updateUsers(String id, UpdateUsersDto updateUsersDto);
}
