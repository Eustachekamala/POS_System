package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.DTO.Supplier.Request.CreateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierResponseDto;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierSummary;
import com.eustache.pos_system.Entities.Product;
import com.eustache.pos_system.Entities.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SupplierMapper {
    /**
     * Converts a CreateSupplierDto to a Supplier entity.
     * @param createSupplierDto CreateSupplierDto
     * @return Supplier
     */
    public Supplier toEntity(CreateSupplierDto createSupplierDto){
        Supplier supplier = new Supplier();
        supplier.setName(createSupplierDto.name());
        supplier.setAddress(createSupplierDto.address());
        supplier.setPhone(createSupplierDto.phone());
        supplier.setEmail(createSupplierDto.email());
        return supplier;
    }

    /**
     * Converts a Supplier entity to a SupplierResponseDto.
     * @param supplier Supplier entity
     * @return SupplierResponseDto
     */
    public SupplierResponseDto toResponseFromSupplier(Supplier supplier){
        List<Product> products = Optional.ofNullable(supplier.getProducts()).orElse(new ArrayList<>());
        return new SupplierResponseDto(
                supplier.getId(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getPhone(),
                supplier.getEmail(),
                products.size()
        );
    }

    /**
     * Converts a Supplier entity to a SupplierSummary.
     * @param supplier Supplier entity
     * @return SupplierSummary
     */
    public SupplierSummary toSummary(Supplier supplier){
        return new SupplierSummary(
                supplier.getId(),
                supplier.getName()
        );
    }
}
