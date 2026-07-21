package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.Product.Request.CreateProductDto;
import com.eustache.pos_system.DTO.Product.Request.UpdateProductDto;
import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.Services.Product.ProductServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "Controller for managing products")
public class ProductController {
    private final ProductServicesImpl productServices;


    @PostMapping("/create")
    @Operation(summary = "Create a new product")
    public ResponseEntity<String> createProduct(@RequestBody @Valid CreateProductDto createProductDto) {
        return ResponseEntity.ok(productServices.create(createProductDto));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productServices.getAll());
    }

    @GetMapping("/searchById/{id}")
    @Operation(summary = "Get a product by ID")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productServices.getById(id));
    }

    @PatchMapping("/update/{id}")
    @Operation(summary = "Update a product by ID")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody @Valid UpdateProductDto updateProductDto) {
        return ResponseEntity.ok(productServices.update(id, updateProductDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a product by ID")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productServices.delete(id));
    }

    @GetMapping("/searchByName/{name}")
    @Operation(summary = "Search a product by name")
    public ResponseEntity<ProductResponseDto> searchProductByName(@PathVariable String name) {
        return ResponseEntity.ok(productServices.searchByName(name));
    }

    @GetMapping("/searchByBarCode/{barCode}")
    @Operation(summary = "Search a product by barcode")
    public ResponseEntity<ProductResponseDto> searchProductByBarCode(@PathVariable String barCode) {
        return ResponseEntity.ok(productServices.searchByBarcode(barCode));
    }

    @GetMapping("/searchByCategory/{categoryId}")
    @Operation(summary = "Search products by category")
    public ResponseEntity<List<ProductResponseDto>> searchProductByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productServices.searchByCategory(categoryId));
    }

}
