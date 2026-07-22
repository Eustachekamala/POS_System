package com.eustache.pos_system.DTO.Category.Response;

import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.DTO.Product.Response.ProductSummary;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Category}
 */
public record CategoryResponseDto(
        Long id,
        String name,
        String description,
        List<ProductSummary> products) implements Serializable {
}