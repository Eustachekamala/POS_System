package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}