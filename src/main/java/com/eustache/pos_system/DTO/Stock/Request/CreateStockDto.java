package com.eustache.pos_system.DTO.Stock.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Stock}
 */
public record CreateStockDto(
        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity must be positive")
        Double quantity,
        @NotNull(message = "Minimum quantity cannot be null")
        @Positive(message = "Minimum quantity must be positive")
        @Min(message = "Minimum quantity must be greater than or equal to 0", value = 10)
        Integer minQuantity) implements Serializable {
}