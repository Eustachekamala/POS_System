package com.eustache.pos_system.Services.LoyaltyCard;

import com.eustache.pos_system.DTO.LoyaltyTransactions.Request.LoyaltyTransactionRequest;
import com.eustache.pos_system.DTO.LoyaltyTransactions.Response.LoyaltyTransactionResponseDto;
import com.eustache.pos_system.Entities.Customer;
import com.eustache.pos_system.Entities.Sale;
import com.eustache.pos_system.Helpers.LoyaltyTransactionType;

import java.util.List;

public interface LoyaltyServices {
    void addPoints(Customer customer, Sale savedSale, double amount);
    void spendPoints(Customer customer, Sale savedSale, double amount);
    void removePoints(Customer customer, Sale sale);
    List<LoyaltyTransactionResponseDto> spendPoints(Long customerId, LoyaltyTransactionRequest request);
    int getLoyaltyPoints(Long customerId);
    List<LoyaltyTransactionResponseDto> getLoyaltyTransactions(Long customerId);
    List<LoyaltyTransactionResponseDto> getLoyaltyHistory(Long customerId);
    int getLoyaltyBalance(Long customerId);
    List<LoyaltyTransactionResponseDto> getLoyaltyTransactionsByType(Long customerId, LoyaltyTransactionType type);
}
