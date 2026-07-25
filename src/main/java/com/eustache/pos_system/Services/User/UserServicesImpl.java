package com.eustache.pos_system.Services.User;

import com.eustache.pos_system.DTO.User.Request.CreateUserDto;
import com.eustache.pos_system.DTO.User.Request.UpdateUserDto;
import com.eustache.pos_system.DTO.User.Response.UserResponseDto;
import com.eustache.pos_system.Entities.User;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Helpers.RoleEnum;
import com.eustache.pos_system.Mappers.UserMapper;
import com.eustache.pos_system.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Creates a new cashier.
     * @param request CreateUserDto
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto createCashier(CreateUserDto request) {
        User cashier = userMapper.toEntity(request);
        userRepository.save(cashier);
        return userMapper.toResponseFromUser(cashier);
    }

    /**
     * Creates a new manager.
     * @param request CreateUserDto
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto createManager(CreateUserDto request) {
        User manager = userMapper.toEntity(request);
        userRepository.save(manager);
        return userMapper.toResponseFromUser(manager);
    }

    /**
     * Creates a new admin.
     * @param request CreateUserDto
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto createAdmin(CreateUserDto request) {
        User admin = userMapper.toEntity(request);
        userRepository.save(admin);
        return userMapper.toResponseFromUser(admin);
    }

    /**
     * Updates an existing user.
     * @param id Long
     * @param updateUserDto UpdateUserDto
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto updateUser(Long id, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("User not found")
        );
        Optional.ofNullable(updateUserDto.username()).ifPresent(user::setUsername);
        Optional.ofNullable(updateUserDto.password()).ifPresent(user::setPassword);
        Optional.ofNullable(updateUserDto.firstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(updateUserDto.lastName()).ifPresent(user::setLastName);
        Optional.ofNullable(updateUserDto.email()).ifPresent(email -> {
            user.setEmail(email);
            user.setUsername(email);
        });
        Optional.ofNullable(updateUserDto.phone()).ifPresent(user::setPhone);
        Optional.ofNullable(updateUserDto.address()).ifPresent(user::setAddress);
        Optional.ofNullable(updateUserDto.role()).ifPresent(user::setRole);
        userRepository.save(user);
        return userMapper.toResponseFromUser(user);
    }

    /**
     * Deletes an existing user.
     * @param id Long
     */
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("User not found")
        );
        userRepository.delete(user);
    }

    /**
     * Returns a list of all users.
     * @return List<UserResponseDto>
     */
    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseFromUser)
                .toList();
    }

    /**
     * Returns a user by id.
     * @param id Long
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("User not found")
        );
        return userMapper.toResponseFromUser(user);
    }

    /**
     * Returns a user by username.
     * @param username String
     * @return UserResponseDto
     */
    @Override
    public UserResponseDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new BusinessException("User not found")
        );
        return userMapper.toResponseFromUser(user);
    }

    /**
     * Returns a list of users by role.
     * @param role RoleEnum
     * @return List<UserResponseDto>
     */
    @Override
    public List<UserResponseDto> getUsersByRole(RoleEnum role) {
        return userRepository.findByRole(role).stream()
                .map(userMapper::toResponseFromUser)
                .toList();
    }
}
