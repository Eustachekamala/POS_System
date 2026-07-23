package com.eustache.pos_system.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    /**
     * One stock belongs to one product
     */
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id" , foreignKey = @ForeignKey(
            name = "fk_stock_product"
    ))
    private Product product;
    @Column(
            nullable = false
    )
    private int minQuantity;
    private LocalDate lastUpdated;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}
