package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}