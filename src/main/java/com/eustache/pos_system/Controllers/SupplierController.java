package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.Product.Response.ProductSummary;
import com.eustache.pos_system.DTO.Supplier.Request.CreateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Request.UpdateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierResponseDto;
import com.eustache.pos_system.Services.Supplier.SupplierServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
@Tag(name = "Supplier Controller", description = "API endpoints for supplier management")
public class SupplierController {
    private final SupplierServicesImpl supplierServices;

    @PostMapping
    @Operation(summary = "Create a new supplier", description = "Create a new supplier by providing supplier details")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Supplier created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<SupplierResponseDto> createSupplier(@RequestBody CreateSupplierDto createSupplierDto) {
        SupplierResponseDto supplierResponseDto = supplierServices.createSupplier(createSupplierDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(supplierResponseDto.id()).toUri();
        return ResponseEntity.created(location).body(supplierResponseDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing supplier", description = "Update an existing supplier by providing supplier details")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated supplier by ID"),
                    @ApiResponse(responseCode = "404", description = "Supplier not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<SupplierResponseDto> updateSupplier(
            @PathVariable Long id,
            @RequestBody UpdateSupplierDto updateSupplierDto) {
        return ResponseEntity.ok(supplierServices.updateSupplier(id, updateSupplierDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a supplier", description = "Delete a supplier by providing supplier ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted supplier by ID"),
                    @ApiResponse(responseCode = "404", description = "Supplier not found")
            }
    )
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all suppliers", description = "Get all suppliers")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved all suppliers"),
                    @ApiResponse(responseCode = "404", description = "No suppliers found"),
            }
    )
    public ResponseEntity<List<SupplierResponseDto>> getAllSuppliers() {
        return ResponseEntity.ok(supplierServices.getAllSuppliers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a supplier by ID", description = "Get a supplier by providing supplier ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved supplier by ID"),
                    @ApiResponse(responseCode = "404", description = "Supplier not found")
            }
    )
    public ResponseEntity<SupplierResponseDto> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierServices.getSupplierById(id));
    }

    @GetMapping("/name")
    @Operation(summary = "Search suppliers by name", description = "Search suppliers by providing supplier name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved suppliers by name"),
                    @ApiResponse(responseCode = "404", description = "No suppliers found")
            }
    )
    public ResponseEntity<List<SupplierResponseDto>> searchSuppliers(@RequestParam String name) {
        return ResponseEntity.ok(supplierServices.searchSuppliers(name));
    }

    @GetMapping("/{id}/products")
    @Operation(summary = "Get products by supplier ID", description = "Get products by providing supplier ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved products by supplier ID"),
                    @ApiResponse(responseCode = "404", description = "No products found")
            }
    )
    public ResponseEntity<List<ProductSummary>> getProductsBySupplierId(@PathVariable Long id) {
        return ResponseEntity.ok(supplierServices.getProductsBySupplier(id));
    }
}
