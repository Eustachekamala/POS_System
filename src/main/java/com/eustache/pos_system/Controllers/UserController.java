package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.User.Request.CreateUserDto;
import com.eustache.pos_system.DTO.User.Request.UpdateUserDto;
import com.eustache.pos_system.DTO.User.Response.UserResponseDto;
import com.eustache.pos_system.Helpers.RoleEnum;
import com.eustache.pos_system.Services.User.UserServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "API endpoints for user management")
public class UserController {
    private final UserServicesImpl userServices;

    @PostMapping("/create")
    @Operation(summary = "Create a new user", description = "Create a new user by providing user details")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        return ResponseEntity.ok(userServices.createUser(createUserDto));
    }

    @PatchMapping("/update/{id}")
    @Operation(summary = "Update an existing user", description = "Update an existing user by providing user details")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userServices.updateUser(id, updateUserDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a user", description = "Delete a user by providing user ID")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userServices.deleteUser(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all users", description = "Get all users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userServices.getAllUsers());
    }

    @GetMapping("/searchById/{id}")
    @Operation(summary = "Get a user by ID", description = "Get a user by providing user ID")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userServices.getUserById(id));
    }

    @GetMapping("/searchByEmail/{email}")
    @Operation(summary = "Get a user by email", description = "Get a user by providing user email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userServices.getUserByEmail(email));
    }

    @GetMapping("/searchByUsername/{username}")
    @Operation(summary = "Get a user by username", description = "Get a user by providing username")
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userServices.getUserByUsername(username));
    }

    @GetMapping("/searchByRole/{role}")
    @Operation(summary = "Get users by role", description = "Get users by providing a role (ADMIN, CUSTOMER, MANAGER, CASHIER)")
    public ResponseEntity<List<UserResponseDto>> getUsersByRole(@PathVariable RoleEnum role) {
        return ResponseEntity.ok(userServices.getUsersByRole(role));
    }

    @GetMapping("/searchByName/{name}")
    @Operation(summary = "Search users by name", description = "Search users by first name or last name")
    public ResponseEntity<List<UserResponseDto>> searchUsers(@PathVariable String name) {
        return ResponseEntity.ok(userServices.searchUsers(name));
    }
}
