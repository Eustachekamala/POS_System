package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.User.Request.CreateUserDto;
import com.eustache.pos_system.DTO.User.Request.UpdateUserDto;
import com.eustache.pos_system.DTO.User.Response.UserResponseDto;
import com.eustache.pos_system.Helpers.RoleEnum;
import com.eustache.pos_system.Services.User.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "API endpoints for user management")
public class UserController {
    private final UserServices userServices;

    @PostMapping("/cashier")
    @Operation(summary = "Create a new cashier", description = "Create a new cashier by providing user details")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cashier created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<UserResponseDto> createCashier(@RequestBody @Valid CreateUserDto request) {
        UserResponseDto cashierResponseDto = userServices.createCashier(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cashierResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).body(cashierResponseDto);
    }

    @PostMapping("/manager")
    @Operation(summary = "Create a new manager", description = "Create a new manager by providing user details")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Manager created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<UserResponseDto> createManager(@RequestBody @Valid CreateUserDto request) {
        UserResponseDto managerResponseDto = userServices.createManager(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(managerResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).body(managerResponseDto);
    }

    @PostMapping("/admin")
    @Operation(summary = "Create a new admin", description = "Create a new admin by providing user details")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Admin created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<UserResponseDto> createAdmin(@RequestBody @Valid CreateUserDto request) {
        UserResponseDto adminResponseDto = userServices.createAdmin(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adminResponseDto.id())
                .toUri();
        return ResponseEntity.created(location).body(adminResponseDto);
    }



    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing user", description = "Update an existing user by providing user details")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userServices.updateUser(id, updateUserDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete a user by providing user ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
            }
    )
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Get all users")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "No users found")
            }
    )
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userServices.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID", description = "Get a user by providing user ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userServices.getUserById(id));
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get a user by username", description = "Get a user by providing username")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userServices.getUserByUsername(username));
    }

    @GetMapping("/role/{role}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "No users found")
            }
    )
    @Operation(summary = "Get users by role", description = "Get users by providing a role (ADMIN, CUSTOMER, MANAGER, CASHIER)")
    public ResponseEntity<List<UserResponseDto>> getUsersByRole(@PathVariable RoleEnum role) {
        return ResponseEntity.ok(userServices.getUsersByRole(role));
    }
}
