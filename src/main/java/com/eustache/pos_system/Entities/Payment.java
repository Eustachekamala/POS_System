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
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship with Sale
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "fk_payment_sale"
    ))
    private Sale sale;
    @Column(
            nullable = false
    )
    private double amount;
    @Column(
            nullable = false
    )
    private double change;
    @Column(
            nullable = false
    )
    private double balance;
    @Column(
            nullable = false
    )
    private LocalDate paymentDate;
    @Column(
            nullable = false
    )
    private String note;
    @Column(
            nullable = false
    )
    private String reference;
}
