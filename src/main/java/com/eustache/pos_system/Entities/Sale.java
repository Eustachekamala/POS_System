package com.eustache.pos_system.Entities;

import com.eustache.pos_system.Helpers.PaymentMethod;
import com.eustache.pos_system.Helpers.StatusPayment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cashier who created the sale
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "fk_sale_user"
    ))
    @JsonIgnore
    private User cashier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "fk_sale_customer"
    ))
    @JsonIgnore
    private Customer customer;

    @Column(
            nullable = false
    )
    private LocalDateTime saleDate;
    @Column(
            nullable = false
    )
    private double totalAmount;
    @Column(
            nullable = false
    )
    private double discount;
    private double finalAmount;
    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 100
    )
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 100
    )
    private StatusPayment status;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleItem> saleItems = new ArrayList<>();

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();
}
