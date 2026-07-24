package com.eustache.pos_system.DTO.SaleItem.Request;

import com.eustache.pos_system.Helpers.PaymentMethod;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.SaleItem}
 */
public record CreateSaleItemDto(
        Long productId,
        Integer quantity) implements Serializable {
}