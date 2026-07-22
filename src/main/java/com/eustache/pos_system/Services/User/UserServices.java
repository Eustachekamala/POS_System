package com.eustache.pos_system.Services.User;

import com.eustache.pos_system.DTO.User.Request.CreateUserDto;
import com.eustache.pos_system.DTO.User.Request.UpdateUserDto;
import com.eustache.pos_system.DTO.User.Response.UserResponseDto;
import com.eustache.pos_system.Helpers.RoleEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserServices {
    String createUser(CreateUserDto createUserDto);
    String updateUser(Long id, UpdateUserDto updateUserDto);
    String deleteUser(Long id);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto getUserByEmail(String email);
    UserResponseDto getUserByUsername(String username);
    List<UserResponseDto> getUsersByRole(RoleEnum role);
    List<UserResponseDto> searchUsers(String name);
}
