package com.eustache.pos_system.Services.Category;

import com.eustache.pos_system.DTO.Category.Request.CreateCategoryDto;
import com.eustache.pos_system.DTO.Category.Request.UpdateCategoryDto;
import com.eustache.pos_system.DTO.Category.Response.CategoryResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryServices {
    List<CategoryResponseDto> findAll();
    CategoryResponseDto findById(Long id);
    CategoryResponseDto create(CreateCategoryDto createCategoryDto);
    CategoryResponseDto update(Long id, UpdateCategoryDto updateCategoryDto);
    void delete(Long id);
    CategoryResponseDto searchByName(String name);
}
