package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}