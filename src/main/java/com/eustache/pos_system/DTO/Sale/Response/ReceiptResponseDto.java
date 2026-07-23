package com.eustache.pos_system.DTO.Sale.Response;

import com.eustache.pos_system.DTO.SaleItem.Request.SaleItemDto;
import com.eustache.pos_system.Helpers.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

public record ReceiptResponseDto(
        Long saleId,
        String cashierName,
        LocalDateTime date,
        List<SaleItemDto> items,
        double total,
        double discount,
        double finalAmount,
        PaymentMethod paymentMethod
) {
}
