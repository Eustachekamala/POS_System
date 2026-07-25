package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.LoyaltyTransactions.Request.CreateLoyaltyTransactionDto;
import com.eustache.pos_system.DTO.LoyaltyTransactions.Response.LoyaltyTransactionResponseDto;
import com.eustache.pos_system.Entities.Customer;
import com.eustache.pos_system.Entities.LoyaltyTransaction;
import com.eustache.pos_system.Entities.Sale;
import com.eustache.pos_system.Helpers.LoyaltyTransactionType;
import org.springframework.stereotype.Component;

@Component
public class LoyaltyTransactionMapper {
//    /**
//     * Converts a CreateLoyaltyTransactionDto to a LoyaltyTransaction entity.
//     * @param dto CreateLoyaltyTransactionDto
//     * @param customer Customer entity
//     * @param sale Sale entity
//     * @return LoyaltyTransaction
//     */
//    public LoyaltyTransaction toEntity(CreateLoyaltyTransactionDto dto, Customer customer, Sale sale) {
//        LoyaltyTransaction transaction = new LoyaltyTransaction();
//        transaction.setCustomer(customer);
//        transaction.setSale(sale);
//        transaction.setPointsEarned(dto.pointsEarned());
//        transaction.setPointsSpent(dto.pointsSpent());
//        transaction.setType(dto.type());
//        return transaction;
//    }

    /**
     * Converts a LoyaltyTransaction entity to a LoyaltyTransaction entity.
     * @param customer Customer Entity
     * @param sale Sale Entity
     * @param pointsEarned Integer
     * @param pointsSpent Integer
     * @param pointsBalance Integer
     * @param type LoyaltyTransactionType
     * @return LoyaltyTransaction Entity
     */
    public LoyaltyTransaction toEntity(
            Customer customer,
            Sale sale,
            int pointsEarned,
            int pointsSpent,
            int pointsBalance,
            LoyaltyTransactionType type
    ) {

        LoyaltyTransaction transaction = new LoyaltyTransaction();

        transaction.setCustomer(customer);
        transaction.setSale(sale);
        transaction.setPointsEarned(pointsEarned);
        transaction.setPointsSpent(pointsSpent);
        transaction.setPointsBalance(pointsBalance);
        transaction.setType(type);

        return transaction;
    }

    /**
     * Converts a LoyaltyTransaction entity to a LoyaltyTransactionResponseDto.
     * @param transaction LoyaltyTransaction entity
     * @return LoyaltyTransactionResponseDto
     */
    public LoyaltyTransactionResponseDto toResponseFromLoyaltyTransaction(LoyaltyTransaction transaction) {
        String customerName = null;
        if (transaction.getCustomer() != null) {
            customerName = transaction.getCustomer().getFirstName() + " " + transaction.getCustomer().getLastName();
        }

        Long saleId = null;
        if (transaction.getSale() != null) {
            saleId = transaction.getSale().getId();
        }

        Long customerId = null;
        if (transaction.getCustomer() != null) {
            customerId = transaction.getCustomer().getId();
        }

        return new LoyaltyTransactionResponseDto(
                transaction.getId(),
                customerId,
                customerName,
                saleId,
                transaction.getPointsEarned(),
                transaction.getPointsSpent(),
                transaction.getPointsBalance(),
                transaction.getType(),
                transaction.getTransactionDate()
        );
    }
}
