package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
}