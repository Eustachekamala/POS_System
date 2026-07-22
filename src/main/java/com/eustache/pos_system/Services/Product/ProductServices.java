package com.eustache.pos_system.Services.Product;

import com.eustache.pos_system.DTO.Product.Request.CreateProductDto;
import com.eustache.pos_system.DTO.Product.Request.UpdateProductDto;
import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductServices {
    List<ProductResponseDto> getAll();
    ProductResponseDto getById(Long id);
    ProductResponseDto create(CreateProductDto createProductDto);
    ProductResponseDto update(Long id, UpdateProductDto updateProductDto);
    String delete(Long id);
    ProductResponseDto searchByName(String name);
    ProductResponseDto searchByBarcode(String barcode);
    List<ProductResponseDto> searchByCategory(String categoryName);
}
