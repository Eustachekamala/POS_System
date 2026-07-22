package com.eustache.pos_system.DTO.User.Request;

import com.eustache.pos_system.Helpers.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.User}
 */
public record UpdateUserDto(
        @Size(min = 4, message = "Username must be at least 4 characters long")
        String username,
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,
        @Size(min = 2, message = "First name must be at least 2 characters long")
        String firstName,
        @Size(min = 2, message = "Last name must be at least 2 characters long")
        String lastName,
        @Email(message = "Invalid email format")
        String email,
        @Pattern(
                regexp = "^\\+?[1-9]\\d{7,14}$",
                message = "Invalid phone format"
        )
        String phone,
        String address,
        RoleEnum role) implements Serializable {
}