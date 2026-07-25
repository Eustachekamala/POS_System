package com.eustache.pos_system.DTO.LoyaltyTransactions.Request;

import com.eustache.pos_system.Helpers.LoyaltyTransactionType;

public record CreateLoyaltyTransactionDto(
        Long customerId,
        Long saleId,
        int pointsEarned,
        int pointsSpent,
        LoyaltyTransactionType type) {
}
