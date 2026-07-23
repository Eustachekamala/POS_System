package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.Customer;
import com.eustache.pos_system.Entities.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard);
}