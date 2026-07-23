package com.eustache.pos_system.DTO.LoyaltyCard.Request;

import com.eustache.pos_system.Helpers.LoyaltyCardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.LoyaltyCard}
 */
public record CreateLoyaltyCardDto(
        @NotBlank(
                message = "Card number is required"
        )
        String cardNumber,
        @NotBlank(
                message = "QR code is required"
        )
        String qrCode,
        @NotNull(
                message = "Status is required"
        )

        LoyaltyCardStatus status) implements Serializable {
}