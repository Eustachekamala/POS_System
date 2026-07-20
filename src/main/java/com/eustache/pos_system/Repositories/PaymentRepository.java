package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}