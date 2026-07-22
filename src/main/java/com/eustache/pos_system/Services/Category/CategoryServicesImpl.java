package com.eustache.pos_system.Services.Category;

import com.eustache.pos_system.DTO.Category.Request.CreateCategoryDto;
import com.eustache.pos_system.DTO.Category.Request.UpdateCategoryDto;
import com.eustache.pos_system.DTO.Category.Response.CategoryResponseDto;
import com.eustache.pos_system.Entities.Category;
import com.eustache.pos_system.Exceptions.BusinessException;
import com.eustache.pos_system.Mappers.CategoryMapper;
import com.eustache.pos_system.Repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServicesImpl implements CategoryServices{
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponseFromCategory)
                .toList();
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BusinessException("Category not found")
        );
        return categoryMapper.toResponseFromCategory(category);
    }

    @Override
    public CategoryResponseDto create(CreateCategoryDto createCategoryDto) {
        Category category = categoryMapper.toEntity(createCategoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseFromCategory(savedCategory);
    }

    @Override
    public CategoryResponseDto update(Long id, UpdateCategoryDto updateCategoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BusinessException("Category not found")
        );
        Optional.ofNullable(updateCategoryDto.name()).ifPresent(category::setName);
        Optional.ofNullable(updateCategoryDto.description()).ifPresent(category::setDescription);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseFromCategory(updatedCategory);
    }

    @Override
    public String delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new BusinessException("Category not found")
        );
        categoryRepository.delete(category);
        return "Category deleted successfully";
    }

    @Override
    public CategoryResponseDto searchByName(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(
                () -> new BusinessException("Category not found")
        );
        return categoryMapper.toResponseFromCategory(category);
    }
}
