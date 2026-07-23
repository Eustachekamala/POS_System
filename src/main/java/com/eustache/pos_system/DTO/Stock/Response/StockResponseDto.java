package com.eustache.pos_system.DTO.Stock.Response;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Stock}
 */
public record StockResponseDto(
        Long id,
        Double quantity,
        Integer minQuantity,
        LocalDate lastUpdated,
        LocalDate createdAt,
        LocalDate updatedAt,
        Long productId,
        String productName) implements Serializable {
}