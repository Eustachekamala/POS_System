package com.eustache.pos_system.Controllers;

import com.eustache.pos_system.DTO.Category.Request.CreateCategoryDto;
import com.eustache.pos_system.DTO.Category.Request.UpdateCategoryDto;
import com.eustache.pos_system.DTO.Category.Response.CategoryResponseDto;
import com.eustache.pos_system.Services.Category.CategoryServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping
    @Operation(summary = "Get all categories")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved all categories"),
                    @ApiResponse(responseCode = "404", description = "No categories found")
            }
    )
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryServices.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved category by ID"),
                    @ApiResponse(responseCode = "404", description = "Category not found")
            }
    )
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryServices.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Create a new category with the provided name and description")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Successfully created new category"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
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
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated category"),
                    @ApiResponse(responseCode = "404", description = "Category not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<CategoryResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryDto dto) {

        return ResponseEntity.ok(
                categoryServices.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted category"),
                    @ApiResponse(responseCode = "404", description = "Category not found")
            }
    )
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name")
    @Operation(summary = "Search categories by name", description = "Search categories by name")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved categories by name"),
                    @ApiResponse(responseCode = "404", description = "No categories found")
            }
    )
    public ResponseEntity<CategoryResponseDto> searchCategoriesByName(@RequestParam String name) {
        return ResponseEntity.ok(categoryServices.searchByName(name));
    }
}
