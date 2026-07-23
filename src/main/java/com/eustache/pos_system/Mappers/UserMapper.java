package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.User.Request.CreateUserDto;
import com.eustache.pos_system.DTO.User.Response.UserResponseDto;
import com.eustache.pos_system.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    /**
     * Converts a CreateUserDto to a User entity.
     * @param createUserDto CreateUserDto
     * @return User
     */
    public User toEntity(CreateUserDto createUserDto){
        User user = new User();
        user.setUsername(createUserDto.email());
        user.setPassword(createUserDto.password());
        user.setFirstName(createUserDto.firstName());
        user.setLastName(createUserDto.lastName());
        user.setEmail(createUserDto.email());
        user.setPhone(createUserDto.phone());
        user.setAddress(createUserDto.address());
        user.setRole(createUserDto.role());
        return user;
    }

    /**
     * Converts a User entity to a UserResponseDto.
     * @param user User entity
     * @return UserResponseDto
     */
    public UserResponseDto toResponseFromUser(User user){
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getRole()
        );
    }
}
