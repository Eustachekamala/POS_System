package com.eustache.pos_system.DTO.SaleItem.Response;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.SaleItem}
 */
public record SaleItemResponseDto(
        String productName,
        int quantity,
        double unitPrice,
        double subtotal) implements Serializable {
}