package com.eustache.pos_system.Repositories;

import com.eustache.pos_system.Entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}