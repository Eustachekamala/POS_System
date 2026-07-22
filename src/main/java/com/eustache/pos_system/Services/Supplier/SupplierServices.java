package com.eustache.pos_system.Services.Supplier;

import com.eustache.pos_system.DTO.Product.Response.ProductSummary;
import com.eustache.pos_system.DTO.Supplier.Request.CreateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Request.UpdateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SupplierServices {
    SupplierResponseDto createSupplier(CreateSupplierDto createSupplierDto);
    SupplierResponseDto updateSupplier(Long id, UpdateSupplierDto updateSupplierDto);
    void deleteSupplier(Long id);
    List<SupplierResponseDto> getAllSuppliers();
    SupplierResponseDto getSupplierById(Long id);
    List<SupplierResponseDto> searchSuppliers(String name);
    List<ProductSummary> getProductsBySupplier(Long id);
}
