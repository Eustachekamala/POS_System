package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.Category.Request.CreateCategoryDto;
import com.eustache.pos_system.DTO.Category.Request.UpdateCategoryDto;
import com.eustache.pos_system.DTO.Category.Response.CategoryResponseDto;
import com.eustache.pos_system.Services.Category.CategoryServices;
import com.eustache.pos_system.Services.Category.CategoryServicesImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "Controller for managing categories")
public class CategoryController {
    private final CategoryServices categoryServices;

    @GetMapping("/all")
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryServices.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryServices.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Create a new category with the provided name and description")
    public ResponseEntity<CategoryResponseDto> create(
            @Valid @RequestBody CreateCategoryDto dto) {

        CategoryResponseDto created =
                categoryServices.create(dto);

        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(created.id())
                        .toUri();

        return ResponseEntity.created(location)
                .body(created);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing category", description = "Update an existing category by ID")
    public ResponseEntity<CategoryResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryDto dto) {

        return ResponseEntity.ok(
                categoryServices.update(id, dto)
        );
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a category", description = "Delete a category by ID")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryServices.delete(id));
    }

    @GetMapping
    @Operation(summary = "Search categories by name", description = "Search categories by name")
    public ResponseEntity<CategoryResponseDto> searchCategoriesByName(@RequestParam String name) {
        return ResponseEntity.ok(categoryServices.searchByName(name));
    }
}
