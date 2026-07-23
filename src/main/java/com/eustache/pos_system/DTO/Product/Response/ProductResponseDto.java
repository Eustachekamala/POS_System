package com.eustache.pos_system.DTO.Product.Response;

import com.eustache.pos_system.DTO.Category.Response.CategorySummary;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierSummary;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Product}
 */
public record ProductResponseDto(
        Long id,
        String name,
        String description,
        String barcode,
        Double sellingPrice,
        CategorySummary category,
        SupplierSummary supplier) implements Serializable {
}