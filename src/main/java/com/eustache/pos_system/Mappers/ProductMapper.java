package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Category.Response.CategorySummary;
import com.eustache.pos_system.DTO.Product.Request.CreateProductDto;
import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.DTO.Product.Response.ProductSummary;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierSummary;
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
        CategorySummary category = new CategorySummary(
                product.getCategory().getId(),
                product.getCategory().getName());
        SupplierSummary supplier = new SupplierSummary(
                product.getSupplier().getId(),
                product.getSupplier().getName());
        return  new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBarcode(),
                product.getSellingPrice(),
                product.getQuantity(),
                category,
                supplier
        );
    }


    public ProductSummary toProductSummary(Product product){
        return new ProductSummary(
                product.getId(),
                product.getName(),
                product.getBarcode(),
                product.getSellingPrice(),
                product.getQuantity()
        );
    }
}
