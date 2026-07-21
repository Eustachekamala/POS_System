package com.eustache.pos_system.DTO.Supplier.Request;

import com.eustache.pos_system.Entities.Supplier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link Supplier}
 */
public record UpdateSupplierDto(
        @Size(min = 2, max = 100 , message = "Name must be at least 2 characters long")
        String name,
        @Size(min = 2, max = 100 , message = "Address must be at least 2 characters long")
        String address,
        @Pattern(
                regexp = "^\\d{10}$",
                message = "Phone number must be 10 digits"
        )
        String phone,
        @Email
        String email) implements Serializable {
}