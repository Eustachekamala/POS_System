package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.Supplier.Request.CreateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Request.UpdateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierResponseDto;
import com.eustache.pos_system.Services.Supplier.SupplierServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
@Tag(name = "Supplier Controller", description = "API endpoints for supplier management")
public class SupplierController {
    private final SupplierServicesImpl supplierServices;

    @PostMapping("/create")
    @Operation(summary = "Create a new supplier", description = "Create a new supplier by providing supplier details")
    public ResponseEntity<String> createSupplier(@RequestBody CreateSupplierDto createSupplierDto) {
        return ResponseEntity.ok(supplierServices.createSupplier(createSupplierDto));
    }

    @PatchMapping("/update/{id}")
    @Operation(summary = "Update an existing supplier", description = "Update an existing supplier by providing supplier details")
    public ResponseEntity<String> updateSupplier(@PathVariable Long id, @RequestBody UpdateSupplierDto updateSupplierDto) {
        return ResponseEntity.ok(supplierServices.updateSupplier(id, updateSupplierDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a supplier", description = "Delete a supplier by providing supplier ID")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        return ResponseEntity.ok(supplierServices.deleteSupplier(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Get all suppliers", description = "Get all suppliers")
    public ResponseEntity<List<SupplierResponseDto>> getAllSuppliers() {
        return ResponseEntity.ok(supplierServices.getAllSuppliers());
    }

    @GetMapping("/searchById/{id}")
    @Operation(summary = "Get a supplier by ID", description = "Get a supplier by providing supplier ID")
    public ResponseEntity<SupplierResponseDto> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierServices.getSupplierById(id));
    }

    @GetMapping("/searchByName/{name}")
    @Operation(summary = "Search suppliers by name", description = "Search suppliers by providing supplier name")
    public ResponseEntity<List<SupplierResponseDto>> searchSuppliers(@PathVariable String name) {
        return ResponseEntity.ok(supplierServices.searchSuppliers(name));
    }
}
