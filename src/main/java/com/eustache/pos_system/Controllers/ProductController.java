package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.Product.Request.CreateProductDto;
import com.eustache.pos_system.DTO.Product.Request.UpdateProductDto;
import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.Services.Product.ProductServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "Controller for managing products")
public class ProductController {
    private final ProductServices productServices;


    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Successfully created new product"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid CreateProductDto createProductDto) {
        ProductResponseDto productResponseDto = productServices.create(createProductDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productResponseDto.id())
                .toUri();

        return ResponseEntity.created(location)
                .body(productResponseDto);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieves all products")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved all products"),
                    @ApiResponse(responseCode = "404", description = "No products found"),
            }
    )
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productServices.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID", description = "Get product by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved product by ID"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            }
    )
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productServices.getById(id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a product by ID", description = "Update product with the provided fields")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated product by ID"),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProductDto updateProductDto) {
        return ResponseEntity.ok(productServices.update(id, updateProductDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by ID", description = "Delete product by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted product by ID"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            }
    )
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productServices.delete(id));
    }

    @GetMapping("/name")
    @Operation(summary = "Search a product by name", description = "Search product by name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved product by name"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            }
    )
    public ResponseEntity<ProductResponseDto> searchProductByName(@RequestParam String name) {
        return ResponseEntity.ok(productServices.searchByName(name));
    }

    @GetMapping("/barcode")
    @Operation(summary = "Search a product by barcode", description = "Search product by barcode")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved product by barcode"),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            }
    )
    public ResponseEntity<ProductResponseDto> searchProductByBarCode(@RequestParam String barCode) {
        return ResponseEntity.ok(productServices.searchByBarcode(barCode));
    }

    @GetMapping("/category/name/")
    @Operation(summary = "Search products by category", description = "Search products by category name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved products by category name"),
                    @ApiResponse(responseCode = "404", description = "Products not found")
            }
    )
    public ResponseEntity<List<ProductResponseDto>> searchProductByCategory(@RequestParam String categoryName) {
        return ResponseEntity.ok(productServices.searchByCategory(categoryName));
    }

}
