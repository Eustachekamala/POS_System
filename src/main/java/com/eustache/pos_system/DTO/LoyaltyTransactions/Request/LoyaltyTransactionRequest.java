package com.eustache.pos_system.DTO.LoyaltyTransactions.Request;

import java.io.Serializable;

/**
 * DTO for loyalty point transactions (spend/redeem)
 */
public record LoyaltyTransactionRequest(
        Long saleId,
        double amount
) implements Serializable {
}
