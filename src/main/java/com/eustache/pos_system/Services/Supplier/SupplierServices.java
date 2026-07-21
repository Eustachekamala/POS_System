package com.eustache.pos_system.Services.Supplier;

import com.eustache.pos_system.DTO.Supplier.Request.CreateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Request.UpdateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SupplierServices {
    String createSupplier(CreateSupplierDto createSupplierDto);
    String updateSupplier(Long id, UpdateSupplierDto updateSupplierDto);
    String deleteSupplier(Long id);
    List<SupplierResponseDto> getAllSuppliers();
    SupplierResponseDto getSupplierById(Long id);
    List<SupplierResponseDto> searchSuppliers(String name);
}
