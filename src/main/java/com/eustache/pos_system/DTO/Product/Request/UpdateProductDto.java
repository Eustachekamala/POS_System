package com.eustache.pos_system.DTO.Product.Request;

import com.eustache.pos_system.DTO.Stock.Request.UpdateStockDto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Product}
 */
public record UpdateProductDto(
        @Size(max = 100, message = "Name must be less than 100 characters")
        String name,
        String description,
        @Positive(message = "Price must be positive")
        Double price,
        @Size(max = 100, message = "Barcode must be less than 100 characters")
        String barcode,
        @Positive(message = "Purchase price must be positive")
        Double purchasePrice,
        @Positive(message = "Selling price must be positive")
        Double sellingPrice,
        Long categoryId,
        Long supplierId,
        @FutureOrPresent(message = "Expiry date cannot be in the past")
        LocalDate expiryDate,
        UpdateStockDto stock) implements Serializable {
}