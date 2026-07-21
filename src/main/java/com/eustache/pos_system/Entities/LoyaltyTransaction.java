package com.eustache.pos_system.Entities;

import com.eustache.pos_system.Helpers.LoyaltyTransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoyaltyTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Many loyalty transactions can have one customer
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "fk_loyalty_transaction_customer"
    ))
    private Customer customer;

    /**
     * Many loyalty transactions can have one sale
     */
    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "fk_loyalty_transaction_sale"
    ))
    private Sale sale;

    @Column(
            nullable = false
    )
    private int pointsEarned;

    @Column(
            nullable = false
    )
    private int pointsSpent;

    @Column(
            nullable = false
    )
    private int pointsBalance;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false
    )
    private LoyaltyTransactionType type;

    @CreationTimestamp
    private LocalDateTime transactionDate;
}
