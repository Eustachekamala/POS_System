package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Product.Request.CreateProductDto;
import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.Entities.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductMapper {
    /**
     *  Converts a CreateProductDto to a Product entity.
     * @param createProductDto
     * @return Product entity
     */
    public Product toEntity(CreateProductDto createProductDto){
        Product product = new Product();
        product.setName(createProductDto.name());
        product.setDescription(createProductDto.description());
        product.setPrice(createProductDto.price());
        product.setBarcode(createProductDto.barcode());
        product.setPurchasePrice(createProductDto.purchasePrice());
        product.setSellingPrice(createProductDto.sellingPrice());
        product.setQuantity(createProductDto.quantity());
        product.setExpiryDate(createProductDto.expiryDate());
        product.setCreatedAt(LocalDate.now());
        return product;
    }

    /**
     *  Converts a Product entity to a ProductResponseDto.
     * @param product
     * @return ProductResponseDto
     */
    public ProductResponseDto toResponseFromProduct(Product product){
        return  new ProductResponseDto(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getBarcode(),
                product.getPurchasePrice(),
                product.getSellingPrice(),
                product.getQuantity(),
                product.getExpiryDate(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getName(),
                product.getSupplier().getName()
        );
    }
}
