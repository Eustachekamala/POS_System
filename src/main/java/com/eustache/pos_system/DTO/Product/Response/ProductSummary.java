package com.eustache.pos_system.DTO.Product.Response;

public record ProductSummary(
        Long id,
        String name,
        String barcode,
        Double sellingPrice
) {
}
