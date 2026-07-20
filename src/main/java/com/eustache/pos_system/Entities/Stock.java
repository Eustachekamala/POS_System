package com.eustache.pos_system.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id" , foreignKey = @ForeignKey(
            name = "fk_stock_product"
    ))
    private Product product;
    @Column(
            nullable = false
    )
    private int minQuantity;
    private LocalDate lastUpdated;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
