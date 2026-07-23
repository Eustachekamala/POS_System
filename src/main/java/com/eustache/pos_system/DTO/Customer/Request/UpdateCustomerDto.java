package com.eustache.pos_system.DTO.Customer.Request;

public record UpdateCustomerDto(
        String firstName,
        String lastName,
        String email,
        String phone,
        int loyaltyPoints,
        String loyaltyCardNumber
) {
}
