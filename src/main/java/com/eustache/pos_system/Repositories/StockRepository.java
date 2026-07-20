package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}