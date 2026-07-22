package com.eustache.pos_system.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false,
            length = 100
    )
    private String firstName;
    @Column(
            nullable = false,
            length = 100
    )
    private String lastName;
    @Column(
            nullable = true,
            length = 100,
            unique = true
    )
    private String email;
    @Column(
            nullable = false,
            length = 100,
            unique = true
    )
    private String phone;
    private int loyaltyPoints;

    /**
     * One customer can have many sales
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales;

    /**
     * One customer can have one loyalty card
     */
    @OneToOne(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private LoyaltyCard loyaltyCard;

    /**
     * One customer can have many loyalty transactions
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoyaltyTransaction> loyaltyTransactions;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
