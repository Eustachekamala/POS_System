package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.DTO.LoyaltyTransactions.Response.LoyaltyTransactionResponseDto;
import com.eustache.pos_system.Entities.LoyaltyTransaction;
import com.eustache.pos_system.Helpers.LoyaltyTransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoyaltyTransactionRepository extends JpaRepository<LoyaltyTransaction, Long> {
    List<LoyaltyTransaction> findByCustomerId(Long customerId);
    List<LoyaltyTransaction> findByCustomerIdOrderByTransactionDateDesc(Long customerId);
    List<LoyaltyTransaction> findByCustomerIdAndType(
            Long customerId,
            LoyaltyTransactionType type
    );
}
