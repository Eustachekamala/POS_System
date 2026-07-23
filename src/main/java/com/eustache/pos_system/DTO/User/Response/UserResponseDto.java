package com.eustache.pos_system.DTO.User.Response;

import com.eustache.pos_system.Helpers.RoleEnum;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.User}
 */
public record UserResponseDto(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        RoleEnum role) implements Serializable {
}
