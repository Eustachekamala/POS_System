package com.eustache.pos_system.Mappers;

import com.eustache.pos_system.DTO.Category.Request.CreateCategoryDto;
import com.eustache.pos_system.DTO.Category.Response.CategoryResponseDto;
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
    private final ProductMapper productMapper;
    /**
     * Converts a CreateCategoryDto to a Category entity.
     * @param categoryDto
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
     * @param category
     * @return CategoryResponseDto
     */
    public CategoryResponseDto toResponseFromCategory(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getProducts().stream().map(productMapper::toProductSummary).toList()
        );
    }
}
