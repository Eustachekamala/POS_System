package com.eustache.pos_system.DTO.Product.Response;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Product}
 */
public record ProductResponseDto(
        Long id,
        String name,
        String description,
        Double price,
        String barcode,
        Double purchasePrice,
        Double sellingPrice,
        Integer quantity,
        LocalDate expiryDate,
        LocalDate createdAt,
        LocalDate updatedAt,
        String categoryNameName,
        String supplierNameName) implements Serializable {
}