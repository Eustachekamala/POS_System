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
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "fk_sale_item_sale"
    ))
    private Sale sale;
    @Column(
            nullable = false
    )
    private int quantity;
    @Column(
            nullable = false
    )
    private double unitPrice;
    private double totalAmount;
}
