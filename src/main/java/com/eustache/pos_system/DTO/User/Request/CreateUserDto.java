package com.eustache.pos_system.DTO.User.Request;

import com.eustache.pos_system.Helpers.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.User}
 */
public record CreateUserDto(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password,
        @NotNull(message = "Role is required")
        RoleEnum role) implements Serializable {
}