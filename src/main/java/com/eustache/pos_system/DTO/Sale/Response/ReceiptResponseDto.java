package com.eustache.pos_system.DTO.Sale.Response;

import com.eustache.pos_system.DTO.SaleItem.Response.SaleItemResponseDto;
import com.eustache.pos_system.Helpers.PaymentMethod;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
/**
 * DTO for {@link com.eustache.pos_system.Entities.Sale}
 */
public record ReceiptResponseDto(
        Long saleId,
        String cashierName,
        LocalDateTime date,
        List<SaleItemResponseDto> items,
        Double total,
        Double discount,
        Double finalAmount,
        PaymentMethod paymentMethod) implements Serializable {
}
