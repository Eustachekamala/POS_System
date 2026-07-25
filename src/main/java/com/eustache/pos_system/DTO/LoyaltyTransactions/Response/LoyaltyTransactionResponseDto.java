package com.eustache.pos_system.DTO.LoyaltyTransactions.Response;

import com.eustache.pos_system.Helpers.LoyaltyTransactionType;

import java.time.LocalDateTime;

public record LoyaltyTransactionResponseDto(
        Long id,
        Long customerId,
        String customerName,
        Long saleId,
        int pointsEarned,
        int pointsSpent,
        int pointsBalance,
        LoyaltyTransactionType type,
        LocalDateTime transactionDate
) {
}
