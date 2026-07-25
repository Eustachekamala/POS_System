package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.Sale;
import com.eustache.pos_system.Helpers.PaymentMethod;
import com.eustache.pos_system.Helpers.StatusPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Sale> findByCashierId(Long cashierId);
    List<Sale> findByCustomerId(Long customerId);

    @Query("""
           SELECT s
           FROM Sale s
           JOIN s.customer c
           WHERE LOWER(CONCAT(c.firstName,' ',c.lastName))
                 LIKE LOWER(CONCAT('%',:customerName,'%'))
           """)
    List<Sale> findByCustomerName(@Param("customerName") String customerName);

    List<Sale> findByPaymentMethod(PaymentMethod paymentMethod);

    List<Sale> findByStatus(StatusPayment status);

    @Query("""
           SELECT COALESCE(SUM(s.finalAmount),0)
           FROM Sale s
           WHERE s.saleDate BETWEEN :startDate AND :endDate
           """)
    BigDecimal findRevenueBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("""
           SELECT COUNT(s)
           FROM Sale s
           WHERE s.saleDate BETWEEN :startDate AND :endDate
           """)
    Long findSalesCountBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}