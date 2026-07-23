package com.eustache.pos_system.DTO.SaleItem.Request;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.SaleItem}
 */
public record SaleItemDto(
        int quantity,
        double unitPrice,
        double subtotal) implements Serializable {
}