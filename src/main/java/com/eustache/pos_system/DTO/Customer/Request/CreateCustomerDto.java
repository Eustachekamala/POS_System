package com.eustache.pos_system.DTO.Customer.Request;

import com.eustache.pos_system.DTO.LoyaltyCard.Request.CreateLoyaltyCardDto;
import com.eustache.pos_system.Entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
public record CreateCustomerDto(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @Email(message = "Email is required")
        String email,
        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^\\+?[1-9]\\d{7,14}$",
                message = "Invalid phone format"
        )
        String phone,
        CreateLoyaltyCardDto loyaltyCard) implements Serializable {
}