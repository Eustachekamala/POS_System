package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Category.Request.CreateCategoryDto;
import com.eustache.pos_system.DTO.Category.Response.CategoryResponseDto;
import com.eustache.pos_system.DTO.Category.Response.CategorySummary;
import com.eustache.pos_system.DTO.Product.Response.ProductSummary;
import com.eustache.pos_system.Entities.Category;
import com.eustache.pos_system.Entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    /**
     * Converts a CreateCategoryDto to a Category entity.
     * @param categoryDto CreateCategoryDto
     * @return Category entity
     */
    public Category toEntity(CreateCategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.name());
        category.setDescription(categoryDto.description());
        return  category;
    }

    /**
     *  Converts a Category entity to a CategoryResponseDto.
     * @param category Category entity
     * @return CategoryResponseDto
     */
    public CategoryResponseDto toResponseFromCategory(Category category) {
        List<ProductSummary> products =
                category.getProducts()
                        .stream()
                        .map(product -> new ProductSummary(
                                product.getId(),
                                product.getName(),
                                product.getBarcode(),
                                product.getSellingPrice()
                        ))
                        .toList();
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                products
        );
    }

    public CategorySummary toSummary(Category category){
        return new CategorySummary(
                category.getId(),
                category.getName()
        );
    }
}
