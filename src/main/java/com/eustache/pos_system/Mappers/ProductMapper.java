package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Category.Response.CategorySummary;
import com.eustache.pos_system.DTO.Product.Request.CreateProductDto;
import com.eustache.pos_system.DTO.Product.Response.ProductResponseDto;
import com.eustache.pos_system.DTO.Product.Response.ProductSummary;
import com.eustache.pos_system.DTO.Stock.Response.StockSummary;
import com.eustache.pos_system.DTO.Supplier.Response.SupplierSummary;
import com.eustache.pos_system.Entities.Product;
import com.eustache.pos_system.Entities.Stock;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Repositories.CategoryRepository;
import com.eustache.pos_system.Repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final StockMapper stockMapper;
    private final SupplierMapper supplierMapper;
    private final CategoryMapper categoryMapper;


    /**
     * Converts CreateProductDto to Product entity
     * and creates the initial Stock.
     *
     * @param createProductDto product creation DTO
     * @return Product entity
     */
    public Product toEntity(CreateProductDto createProductDto) {

        Product product = new Product();

        product.setName(createProductDto.name());
        product.setDescription(createProductDto.description());
        product.setBarcode(createProductDto.barcode());
        product.setPurchasePrice(createProductDto.purchasePrice());
        product.setSellingPrice(createProductDto.sellingPrice());
        product.setExpiryDate(createProductDto.expiryDate());


        /*
         * Set Category
         */
        product.setCategory(
                categoryRepository.findById(createProductDto.categoryId())
                        .orElseThrow(
                                () -> new BusinessException("Category not found")
                        )
        );

        /*
         * Set Supplier
         */
        product.setSupplier(
                supplierRepository.findById(createProductDto.supplierId())
                        .orElseThrow(
                                () -> new BusinessException("Supplier not found")
                        )
        );


        /*
         * Create Stock
         */
        Stock stock = new Stock();

        stock.setQuantity(createProductDto.stock().quantity());
        stock.setMinQuantity(createProductDto.stock().minQuantity());
        stock.setLastUpdated(LocalDate.now());


        /*
         * Establish bidirectional relationship
         */
        stock.setProduct(product);
        product.setStock(stock);


        return product;
    }


    /**
     * Converts Product entity to ProductResponseDto
     */
    public ProductResponseDto toResponseFromProduct(Product product) {
        /*
         * Convert related entities to summaries
         */
        CategorySummary category = categoryMapper.toSummary(product.getCategory());
        /*
         * Convert related entities to summaries
         */
        SupplierSummary supplier = supplierMapper.toSummary(product.getSupplier());
        /*
         * Convert related entities to summaries
         */
        StockSummary stock = stockMapper.toSummary(product.getStock());


        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBarcode(),
                product.getSellingPrice(),
                category,
                supplier,
                stock
        );
    }


    public ProductSummary toProductSummary(Product product) {

        return new ProductSummary(
                product.getId(),
                product.getName(),
                product.getBarcode(),
                product.getSellingPrice()
        );
    }
}