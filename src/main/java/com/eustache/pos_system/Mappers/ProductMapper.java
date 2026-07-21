package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Product.Request.CreateProductDto;
import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.Entities.Product;
import com.eustache.pos_system.Repositories.CategoryRepository;
import com.eustache.pos_system.Repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
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
        product.setCategory(categoryRepository.findById(createProductDto.categoryId()).orElseThrow(
                () -> new RuntimeException("Category not found")
        ));
        product.setSupplier(supplierRepository.findById(createProductDto.supplierId()).orElseThrow(
                () -> new RuntimeException("Supplier not found")
        ));
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
