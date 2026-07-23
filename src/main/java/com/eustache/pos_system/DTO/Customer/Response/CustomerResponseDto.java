package com.eustache.pos_system.DTO.Customer.Response;

import com.eustache.pos_system.DTO.LoyaltyCard.Response.LoyaltyResponseCardDto;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Customer}
 */
public record CustomerResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        int loyaltyPoints,
        LoyaltyResponseCardDto loyaltyCard) implements Serializable {
}