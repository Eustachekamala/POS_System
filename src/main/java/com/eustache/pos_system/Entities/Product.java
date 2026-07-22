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
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;

    /**
     * Many products can have one category
     */
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id" , foreignKey = @ForeignKey(
            name = "fk_product_category"
    ))
    private Category category;

    /**
     * Many products can have one supplier
     */
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id" , foreignKey = @ForeignKey(
            name = "fk_product_supplier"
    ))
    private Supplier supplier;
}
