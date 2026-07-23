package com.eustache.pos_system.DTO.User.Request;

import com.eustache.pos_system.Helpers.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.User}
 */
public record CreateUserDto(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Phone is required")
        @Pattern(
                regexp = "^\\+?[1-9]\\d{7,14}$",
                message = "Invalid phone format"
        )
        String phone,
        @NotBlank(message = "Address is required")
        String address,
        @NotNull(message = "Role is required")
        RoleEnum role) implements Serializable {
}