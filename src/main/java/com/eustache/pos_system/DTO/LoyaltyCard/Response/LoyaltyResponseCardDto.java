package com.eustache.pos_system.DTO.LoyaltyCard.Response;

import com.eustache.pos_system.Helpers.LoyaltyCardStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.eustache.pos_system.Entities.LoyaltyCard}
 */
public record LoyaltyResponseCardDto(
        String cardNumber,
        int points,
        String qrCode,
        LoyaltyCardStatus status) implements Serializable {
}