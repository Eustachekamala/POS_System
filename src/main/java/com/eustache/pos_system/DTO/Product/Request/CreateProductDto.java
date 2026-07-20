package com.eustache.pos_system.DTO.Product.Request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Product}
 */
public record CreateProductDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 100, message = "Name must be less than 100 characters")
        String name,
        @NotBlank(message = "Description cannot be blank")
        String description,
        @NotNull(message = "Price cannot be null")
        Double price,
        @NotBlank(message = "Barcode cannot be blank")
        String barcode,
        @NotNull(message = "Purchase price cannot be null")
        Double purchasePrice,
        @NotNull(message = "Selling price cannot be null")
        Double sellingPrice,
        @NotNull(message = "Quantity cannot be null")
        Integer quantity,
        @FutureOrPresent(message = "Expiry date cannot be in the past")
        LocalDate expiryDate,
        LocalDate createdAt
) implements Serializable {
}