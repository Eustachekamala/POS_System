package com.eustache.pos_system.DTO.Stock.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Stock}
 */
public record UpdateStockDto(
        @Positive(message = "Quantity must be positive")
        Double quantity,
        @Positive(message = "Minimum quantity must be positive")
        @Min(message = "Minimum quantity must be greater than or equal to 0", value = 10)
        Integer minQuantity) implements Serializable {
}