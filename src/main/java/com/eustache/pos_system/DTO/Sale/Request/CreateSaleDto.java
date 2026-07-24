package com.eustache.pos_system.DTO.Sale.Request;

import com.eustache.pos_system.DTO.Payment.Request.CreatePaymentDto;
import com.eustache.pos_system.DTO.SaleItem.Request.CreateSaleItemDto;
import com.eustache.pos_system.Helpers.PaymentMethod;
import com.eustache.pos_system.Helpers.StatusPayment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Sale}
 */
public record CreateSaleDto(
        Long cashierId,
        Long customerId,
        BigDecimal discount,
        List<CreateSaleItemDto> items,
        CreatePaymentDto payment) implements Serializable {
}