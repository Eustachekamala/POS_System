package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.Category.Request.CreateCategoryDto;
import com.eustache.pos_system.DTO.Category.Request.UpdateCategoryDto;
import com.eustache.pos_system.DTO.Category.Response.CategoryResponseDto;
import com.eustache.pos_system.Services.Category.CategoryServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "Controller for managing categories")
public class CategoryController {
    private final CategoryServicesImpl categoryServicesImpl;

    @GetMapping("/all")
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryServicesImpl.findAll());
    }

    @GetMapping("/searchById/{id}")
    @Operation(summary = "Get category by ID")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryServicesImpl.findById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new category", description = "Create a new category")
    public ResponseEntity<String> createCategory(@RequestBody @Valid CreateCategoryDto createCategoryDto) {
        return ResponseEntity.ok(categoryServicesImpl.create(createCategoryDto));
    }

    @PatchMapping("/update/{id}")
    @Operation(summary = "Update an existing category", description = "Update an existing category by ID")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody @Valid UpdateCategoryDto updateCategoryDto) {
        return ResponseEntity.ok(categoryServicesImpl.update(id, updateCategoryDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category by ID")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryServicesImpl.delete(id));
    }

    @PostMapping("/searchByName/{name}")
    @Operation(summary = "Search categories by name", description = "Search categories by name")
    public ResponseEntity<CategoryResponseDto> searchCategoriesByName(@PathVariable String name) {
        return ResponseEntity.ok(categoryServicesImpl.searchByName(name));
    }
}
