package com.eustache.pos_system.DTO.Category.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.eustache.pos_system.Entities.Category}
 */
public record CreateCategoryDto(
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,
        @NotBlank(message = "Description is required")
        String description) implements Serializable {
}