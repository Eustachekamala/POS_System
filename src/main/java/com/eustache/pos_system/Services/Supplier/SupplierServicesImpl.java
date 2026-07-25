package com.eustache.pos_system.Services.Supplier;

import com.eustache.pos_system.DTO.Product.Response.ProductSummary;
import com.eustache.pos_system.DTO.Supplier.Request.CreateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Request.UpdateSupplierDto;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierResponseDto;
import com.eustache.pos_system.Entities.Supplier;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Mappers.ProductMapper;
import com.eustache.pos_system.Mappers.SupplierMapper;
import com.eustache.pos_system.Repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierServicesImpl implements SupplierServices{
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final ProductMapper productMapper;

    /**
     * Creates a new supplier.
     * @param createSupplierDto CreateSupplierDto
     * @return SupplierResponseDto
     */
    @Override
    public SupplierResponseDto createSupplier(CreateSupplierDto createSupplierDto) {
        Supplier supplier = supplierMapper.toEntity(createSupplierDto);
        supplierRepository.save(supplier);
        return supplierMapper.toResponseFromSupplier(supplier);
    }

    /**
     * Updates an existing supplier.
     * @param id Long
     * @param updateSupplierDto UpdateSupplierDto
     * @return SupplierResponseDto
     */
    @Override
    public SupplierResponseDto updateSupplier(Long id, UpdateSupplierDto updateSupplierDto) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(
                () -> new BusinessException("Supplier not found")
        );
        Optional.ofNullable(updateSupplierDto.name()).ifPresent(supplier::setName);
        Optional.ofNullable(updateSupplierDto.email()).ifPresent(supplier::setEmail);
        Optional.ofNullable(updateSupplierDto.phone()).ifPresent(supplier::setPhone);
        Optional.ofNullable(updateSupplierDto.address()).ifPresent(supplier::setAddress);
        supplierRepository.save(supplier);
        return supplierMapper.toResponseFromSupplier(supplier);
    }

    /**
     * Deletes an existing supplier.
     * @param id Long
     */
    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(
                () -> new BusinessException("Supplier not found")
        );
        supplierRepository.delete(supplier);
    }

    /**
     * Gets all suppliers.
     * @return List<SupplierResponseDto>
     */
    @Override
    public List<SupplierResponseDto> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(supplierMapper::toResponseFromSupplier)
                .toList();
    }

    /**
     * Gets a supplier by id.
     * @param id Long
     * @return SupplierResponseDto
     */
    @Override
    public SupplierResponseDto getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(
                () -> new BusinessException("Supplier not found")
        );
        return supplierMapper.toResponseFromSupplier(supplier);
    }

    /**
     * Searches for suppliers by name.
     * @param name String
     * @return List<SupplierResponseDto>
     */
    @Override
    public List<SupplierResponseDto> searchSuppliers(String name) {
        return supplierRepository.findAll().stream()
                .filter(supplier -> supplier.getName().toLowerCase().contains(name.toLowerCase()))
                .map(supplierMapper::toResponseFromSupplier)
                .toList();
    }

    /**
     * Gets a list of products by supplier id.
     * @param id Long
     * @return List<ProductSummary>
     */
    @Override
    public List<ProductSummary> getProductsBySupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(
                () ->new BusinessException("Supplier not found")
        );
        return supplier.getProducts().stream()
                .map(productMapper::toProductSummary)
                .toList();
    }
}
