package com.project.services.impl;

import com.project.config.CustomUsersDetails;
import com.project.config.JwtTokenProvider;
import com.project.dtos.*;
import com.project.entities.*;
import com.project.entities.Users;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.mappers.GetDateNow;
import com.project.repositories.CartRepository;
import com.project.repositories.UsersRepository;
import com.project.services.CartService;
import com.project.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CartService cartService;
    @Override
    public Users findById(String id) {
        Optional<Users> users = usersRepository.findById(id);
        if(!users.isPresent()){
            throw new AppException("Tài khoản không tồn tại","404");
        }
        return users.get();
    }
    @Override
    public UsersDto findByUsername(String username) {
        Users users = usersRepository.findByUsername(username);
        if(users == null){
            throw new AppException("Tài khoản không tồn tại","404");
        }
        return new EntityToDtoMapper().entityToDtoUsers(users);
    }
    @Override
    @Transactional
    public void signUpUser(RegisterDto registerDto) {
        if(usersRepository.existsByUsername(registerDto.getUsername())){
            throw new AppException("Tài khoản đã tồn tại", "409");
        }
        Users users = new Users();
        users.setId(UUID.randomUUID().toString());
        users.setUsername(registerDto.getUsername());
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        users.setPassword(encodedPassword);
        users.setStatus(true);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();
        String dateNow = sdf.format(now);
        try{
            users.setCreatedAt(sdf.parse(dateNow));
            users.setModifiedAt(sdf.parse(dateNow));
        }catch (Exception e){
            e.printStackTrace();
        }
        if(registerDto.getRole() == null){
            registerDto.setRole("USER");
        }
        String roleName = "ROLE_" + registerDto.getRole();
        users.setRole(ERole.valueOf(roleName));
        Users savedUsers = usersRepository.save(users);
        cartService.addCart(savedUsers);
    }

    @Override
    public JwtResponse signInUser(LogInDto logInDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(logInDto.getUsername(), logInDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUsersDetails customUserDetails = (CustomUsersDetails) authentication.getPrincipal();
            String jwt = jwtTokenProvider.generateToken(customUserDetails);
            String role = customUserDetails.getRole();
            return new JwtResponse(jwt, customUserDetails.getUsername(), role);
        }catch (AuthenticationException e){
            throw new AppException("Tên tài khoản hoặc mật khẩu không chính xác","404");
        }
    }

    @Override
    public void changePassword(String username, ChangePasswordDto changePasswordDto) {
        if(usersRepository.findByUsername(username) == null)
            throw new AppException("Tài khoản không tồn tại", "404");
        if(!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword()))
            throw new AppException("Mật khẩu xác nhận không giống mật khẩu mới", "501");
        String oldEncodedPassword = usersRepository.findByUsername(username).getPassword();
        if(!passwordEncoder.matches(changePasswordDto.getOldPassword(),oldEncodedPassword)){
            throw new AppException("Mật khẩu cũ không đúng", "501");
        }
        Users users = usersRepository.findByUsername(username);
        users.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        usersRepository.save(users);
    }

    @Override
    public ProfileDto getProfileByUsername(String username) {
        if(usersRepository.findByUsername(username) == null)
            throw new AppException("Tài khoản không tồn tại", "404");
        Users users = usersRepository.findByUsername(username);
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(users.getId());
        profileDto.setUsername(users.getUsername());
        profileDto.setPhone(users.getPhone());
        profileDto.setEmail(users.getEmail());
        profileDto.setAddress(users.getAddress());
        profileDto.setFullname(users.getFullname());
        return profileDto;
    }

    @Override
    public void updateProfile(String id, UpdateProfileDto updateProfileDto) {
        if(usersRepository.findById(id) == null)
            throw new AppException("Tài khoản không tồn tại", "404");
        Users users = usersRepository.findById(id).get();
        users.setModifiedAt(GetDateNow.getDateNow());
        users.setAddress(updateProfileDto.getAddress());
        users.setEmail(updateProfileDto.getEmail());
        users.setPhone(updateProfileDto.getPhone());
        users.setFullname(updateProfileDto.getFullname());
        Users savedUsers = usersRepository.save(users);
    }

    @Override
    @Transactional
    public UsersDto addUsers(AddUsersDto addUsersDto) {
        if(usersRepository.existsByUsername(addUsersDto.getUsername())){
            throw new AppException("Tài khoản đã tồn tại", "409");
        }
        addUsersDto.setPassword(passwordEncoder.encode(addUsersDto.getPassword()));
        Users users = new DtoToEntityMapper().dtoToEntityAddUsers(addUsersDto);
        Users savedUsers = usersRepository.save(users);
        cartService.addCart(savedUsers);
        return new EntityToDtoMapper().entityToDtoUsers(savedUsers);
    }

    @Override
    public UsersDto updateUsers(String id, UpdateUsersDto updateUsersDto) {
        if(usersRepository.findById(id) == null)
            throw new AppException("Tài khoản không tồn tại", "404");
        Users users = usersRepository.findById(id).get();
        users.setModifiedAt(GetDateNow.getDateNow());
        users.setRole(ERole.valueOf(updateUsersDto.getRole()));
        users.setStatus(updateUsersDto.isStatus());
        users.setAddress(updateUsersDto.getAddress());
        users.setEmail(updateUsersDto.getEmail());
        users.setPhone(updateUsersDto.getPhone());
        users.setFullname(updateUsersDto.getFullname());
        Users savedUsers = usersRepository.save(users);
        return new EntityToDtoMapper().entityToDtoUsers(savedUsers);
    }

    @Override
    public void deleteUsers(String id) {
        if(usersRepository.findById(id) == null)
            throw new AppException("Tài khoản không tồn tại", "404");
        usersRepository.deleteById(id);
    }

    @Override
    public UsersDto getUsersById(String id) {
        if(!usersRepository.findById(id).isPresent())
            throw new AppException("Tài khoản không tồn tại", "404");
        Users users = usersRepository.findById(id).get();
        return new EntityToDtoMapper().entityToDtoUsers(users);
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<UsersDto> usersDtos = usersRepository.findAll().stream()
                .map(users -> new EntityToDtoMapper().entityToDtoUsers(users))
                .collect(Collectors.toList());
        return usersDtos;
    }
}
