package com.eustache.pos_system.DTO.Sale.Response;

import com.eustache.pos_system.Helpers.PaymentMethod;
import com.eustache.pos_system.Helpers.StatusPayment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for Sale entity
 */
public record SaleResponseDto(

        Long id,
        Long customerId,
        String customerName,
        Long cashierId,
        String cashierName,
        LocalDateTime saleDate,
        BigDecimal totalAmount,
        BigDecimal discount,
        BigDecimal finalAmount,
        PaymentMethod paymentMethod,
        StatusPayment status,
        List<SaleItemDto> saleItems,
        List<PaymentDto> payments
) implements Serializable {

    /**
     * DTO for SaleItem entity
     */
    public record SaleItemDto(
            Long productId,
            String productName,
            Integer quantity,
            BigDecimal unitPrice,
            BigDecimal subtotal

    ) implements Serializable {}

    /**
     * DTO for Payment entity
     */
    public record PaymentDto(
            Long paymentId,
            PaymentMethod paymentMethod,
            BigDecimal amountPaid,
            BigDecimal change,
            BigDecimal balance,
            LocalDateTime paymentDate,
            String reference,
            String note

    ) implements Serializable {}
}