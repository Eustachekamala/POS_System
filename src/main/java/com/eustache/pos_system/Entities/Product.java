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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false,
            length = 100
    )
    private String name;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;
    @Column(
            nullable = false
    )
    private double price;
    @Column(
            nullable = false,
            length = 100
    )
    private String barcode;
    @Column(
            nullable = false
    )
    private double purchasePrice;
    @Column(
            nullable = false
    )
    private double sellingPrice;
    @Column(
            nullable = false
    )
    private int quantity;
    @Column(
            nullable = false
    )
    private LocalDate expiryDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id" , foreignKey = @ForeignKey(
            name = "fk_product_category"
    ))
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false, referencedColumnName = "id" , foreignKey = @ForeignKey(
            name = "fk_product_supplier"
    ))
    private Supplier supplier;
}
