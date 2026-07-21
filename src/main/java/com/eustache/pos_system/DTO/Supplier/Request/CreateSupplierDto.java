package com.eustache.pos_system.DTO.Supplier.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Supplier}
 */
public record CreateSupplierDto(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Address is required")
        String address,
        @NotBlank(message = "Phone is required")
        @Pattern(
                regexp = "^\\d{10}$",
                message = "Phone number must be 10 digits"
        )
        String phone,
        @NotBlank(message = "Email is required")
        @Email(message = "Email is not valid")
        String email) implements Serializable {
}