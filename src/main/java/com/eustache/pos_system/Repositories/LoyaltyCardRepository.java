package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long> {
    Optional<LoyaltyCard> findByCardNumber(String cardNumber);
}