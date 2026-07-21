package com.eustache.pos_system.DTO.Supplier.Response;

import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.Entities.Supplier;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Supplier}
 */
public record SupplierResponseDto(
        String name,
        String address,
        String phone,
        String email,
        List<ProductResponseDto> products) implements Serializable {
}