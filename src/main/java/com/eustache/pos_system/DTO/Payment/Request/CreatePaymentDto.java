package com.eustache.pos_system.DTO.Payment.Request;

import com.eustache.pos_system.Helpers.PaymentMethod;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Payment}
 */
public record CreatePaymentDto(
        PaymentMethod paymentMethod,
        BigDecimal amount,
        LocalDate paymentDate,
        String note,
        String reference) implements Serializable {
}