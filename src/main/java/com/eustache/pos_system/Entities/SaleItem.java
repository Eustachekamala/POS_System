package com.eustache.pos_system.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale_items")
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Many sale items can belong to one sale
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "fk_sale_item_sale"
    ))
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_sale_item_product"
            )
    )
    private Product product;

    @Column(
            nullable = false
    )
    private int quantity;
    @Column(
            nullable = false
    )
    /*
     * Product price at the moment of sale
     */
    private double unitPrice;

    private double subtotal;
}
