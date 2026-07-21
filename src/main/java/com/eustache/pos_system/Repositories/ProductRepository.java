package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findByName(String name);
    Product findByBarcode(String barcode);
    List<Product> findByCategoryId(Long categoryId);
}