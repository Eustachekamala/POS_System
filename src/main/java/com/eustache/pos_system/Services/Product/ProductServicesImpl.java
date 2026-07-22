package com.eustache.pos_system.Services.Product;

import com.eustache.pos_system.DTO.Product.Request.CreateProductDto;
import com.eustache.pos_system.DTO.Product.Request.UpdateProductDto;
import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.Entities.Product;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Mappers.ProductMapper;
import com.eustache.pos_system.Repositories.CategoryRepository;
import com.eustache.pos_system.Repositories.ProductRepository;
import com.eustache.pos_system.Repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServicesImpl implements ProductServices {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream().map(productMapper::toResponseFromProduct).toList();
    }

    @Override
    public ProductResponseDto getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new BusinessException("Product not found")
        );
        return productMapper.toResponseFromProduct(product);
    }

    @Override
    public ProductResponseDto create(CreateProductDto createProductDto) {
        Product productCreated = productMapper.toEntity(createProductDto);
        productRepository.save(productCreated);
        return productMapper.toResponseFromProduct(productCreated);
    }

    @Override
    public ProductResponseDto update(Long id, UpdateProductDto updateProductDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new BusinessException("Product not found")
        );
        Optional.ofNullable(updateProductDto.name()).ifPresent(product::setName);
        Optional.ofNullable(updateProductDto.description()).ifPresent(product::setDescription);
        Optional.ofNullable(updateProductDto.price()).ifPresent(product::setPrice);
        Optional.ofNullable(updateProductDto.barcode()).ifPresent(product::setBarcode);
        Optional.ofNullable(updateProductDto.purchasePrice()).ifPresent(product::setPurchasePrice);
        Optional.ofNullable(updateProductDto.sellingPrice()).ifPresent(product::setSellingPrice);
        Optional.ofNullable(updateProductDto.quantity()).ifPresent(product::setQuantity);
        Optional.ofNullable(updateProductDto.categoryId()).ifPresent(
                categoryId -> product
                        .setCategory(categoryRepository
                                .findById(categoryId).orElseThrow(() -> new BusinessException("Category not found"))));
        Optional.ofNullable(updateProductDto.expiryDate()).ifPresent(product::setExpiryDate);
        productRepository.save(product);
        return productMapper.toResponseFromProduct(product);
    }

    @Override
    public String delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new BusinessException("Product not found")
        );
        productRepository.delete(product);
        return "Product deleted successfully";
    }

    @Override
    public ProductResponseDto searchByName(String name) {
        Product product = productRepository.findByName(name);
        if (product == null) {
            throw new BusinessException("Product not found");
        }
        return productMapper.toResponseFromProduct(product);
    }

    @Override
    public ProductResponseDto searchByBarcode(String barcode) {
        Product product = productRepository.findByBarcode(barcode);
        if (product == null) {
            throw new BusinessException("Product not found");
        }
        return productMapper.toResponseFromProduct(product);
    }

    @Override
    public List<ProductResponseDto> searchByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName)
                .stream()
                .map(productMapper::toResponseFromProduct)
                .toList();
    }
}
