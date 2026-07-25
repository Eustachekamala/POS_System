package com.eustache.pos_system.Services.User;

import com.eustache.pos_system.DTO.User.Request.CreateUserDto;
import com.eustache.pos_system.DTO.User.Request.UpdateUserDto;
import com.eustache.pos_system.DTO.User.Response.UserResponseDto;
import com.eustache.pos_system.Helpers.RoleEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserServices {
    UserResponseDto createCashier(CreateUserDto request);
    UserResponseDto createManager(CreateUserDto request);
    UserResponseDto createAdmin(CreateUserDto request);
    UserResponseDto updateUser(Long id, UpdateUserDto updateUserDto);
    void deleteUser(Long id);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto getUserByUsername(String username);
    List<UserResponseDto> getUsersByRole(RoleEnum role);
}
