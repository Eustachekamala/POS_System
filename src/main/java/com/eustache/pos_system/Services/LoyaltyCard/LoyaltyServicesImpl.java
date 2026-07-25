package com.eustache.pos_system.Services.LoyaltyCard;

import com.eustache.pos_system.DTO.LoyaltyTransactions.Request.LoyaltyTransactionRequest;
import com.eustache.pos_system.DTO.LoyaltyTransactions.Response.LoyaltyTransactionResponseDto;
import com.eustache.pos_system.Entities.Customer;
import com.eustache.pos_system.Entities.LoyaltyTransaction;
import com.eustache.pos_system.Entities.Sale;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Helpers.LoyaltyTransactionType;
import com.eustache.pos_system.Mappers.LoyaltyTransactionMapper;
import com.eustache.pos_system.Repositories.CustomerRepository;
import com.eustache.pos_system.Repositories.LoyaltyTransactionRepository;
import com.eustache.pos_system.Repositories.SaleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoyaltyServicesImpl implements LoyaltyServices{
    private final CustomerRepository customerRepository;
    private final LoyaltyTransactionRepository loyaltyTransactionRepository;
    private final SaleRepository saleRepository;
    private final LoyaltyTransactionMapper loyaltyTransactionMapper;

    /**
     * Adds points to a customer's loyalty card.
     * @param customer  Customer Entity
     * @param savedSale Sale Entity
     * @param amount    Double
     */
    @Override
    @Transactional
    public void addPoints(Customer customer, Sale savedSale, double amount) {

        int pointsEarned = (int) amount;
        int newBalance = customer.getLoyaltyPoints() + pointsEarned;

        customer.setLoyaltyPoints(newBalance);
        customerRepository.save(customer);
        /*
         * Create loyalty transaction record
         */
        LoyaltyTransaction transaction =
                loyaltyTransactionMapper.toEntity(
                        customer,
                        savedSale,
                        pointsEarned,
                        0,
                        newBalance,
                        LoyaltyTransactionType.EARN
                );
        loyaltyTransactionRepository.save(transaction);
    }

    /**
     * Spends points from a customer's loyalty card.
     * @param customer Customer Entity
     * @param savedSale Sale EntityRuntimeException
     * @param amount Double
     */
    @Override
    @Transactional
    public void spendPoints(Customer customer, Sale savedSale, double amount) {

        int pointsSpent = (int) amount;

        if (customer.getLoyaltyPoints() < pointsSpent) {
            throw new BusinessException("Insufficient loyalty points");
        }

        int newBalance = customer.getLoyaltyPoints() - pointsSpent;

        customer.setLoyaltyPoints(newBalance);
        customerRepository.save(customer);

        LoyaltyTransaction transaction =
                loyaltyTransactionMapper.toEntity(
                        customer,
                        savedSale,
                        0,
                        pointsSpent,
                        newBalance,
                        LoyaltyTransactionType.REDEEM
                );

        loyaltyTransactionRepository.save(transaction);
    }

    /**
     * Spends points from a customer's loyalty card.
     * @param customerId Long
     * @param request LoyaltyTransactionRequest
     * @return List of LoyaltyTransactionResponseDto
     */
    @Override
    @Transactional
    public List<LoyaltyTransactionResponseDto> spendPoints(Long customerId, LoyaltyTransactionRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found"));

        Sale sale = saleRepository.findById(request.saleId())
                .orElseThrow(() -> new BusinessException("Sale not found"));

        spendPoints(customer, sale, request.amount());

        return getLoyaltyTransactions(customerId);
    }

    /**
     * Retrieves the loyalty points balance for a customer.
     * @param customerId Long
     * @return int
     */
    @Override
    public int getLoyaltyPoints(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found"));
        return customer.getLoyaltyPoints();
    }

    /**
     * Retrieves all loyalty transactions for a customer.
     *
     * @param customerId Long
     * @return List of LoyaltyTransaction
     */
    @Override
    public List<LoyaltyTransactionResponseDto> getLoyaltyTransactions(Long customerId) {

        customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found"));

        return loyaltyTransactionRepository.findByCustomerId(customerId)
                .stream()
                .map(loyaltyTransactionMapper::toResponseFromLoyaltyTransaction)
                .toList();
    }

    /**
     * Retrieves the loyalty transaction history for a customer.
     * @param customerId Long
     * @return List of LoyaltyTransaction
     */
    @Override
    public List<LoyaltyTransactionResponseDto> getLoyaltyHistory(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found"));

        return loyaltyTransactionRepository
                .findByCustomerIdOrderByTransactionDateDesc(customerId)
                .stream()
                .map(loyaltyTransactionMapper::toResponseFromLoyaltyTransaction)
                .toList();
    }

    /**
     * Retrieves the loyalty points balance for a customer.
     * @param customerId Long
     * @return int
     */
    @Override
    public int getLoyaltyBalance(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found"));
        return customer.getLoyaltyPoints();
    }

    /**
     * Retrieves loyalty transactions for a customer by type.
     *
     * @param customerId Long
     * @param type       LoyaltyTransactionType
     * @return List of LoyaltyTransaction
     */
    @Override
    public List<LoyaltyTransactionResponseDto> getLoyaltyTransactionsByType(
            Long customerId,
            LoyaltyTransactionType type) {

        customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found"));

        return loyaltyTransactionRepository
                .findByCustomerIdAndType(customerId, type)
                .stream()
                .map(loyaltyTransactionMapper::toResponseFromLoyaltyTransaction)
                .toList();
    }
}
