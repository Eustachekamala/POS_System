package com.eustache.pos_system.Entities;

import com.eustache.pos_system.Helpers.LoyaltyCardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loyalty_cards")
public class LoyaltyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false,
            length = 100,
            unique = true
    )
    private String cardNumber;
    @Column(
            nullable = false,
            length = 100,
            unique = true
    )
    private String qrCode;
    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false
    )
    private LoyaltyCardStatus status;
    @Column(
            nullable = false
    )
    @CreationTimestamp
    private LocalDateTime issueAt;

    /**
     * One loyalty card can have one customer
     */
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "fk_loyalty_card_customer"
    ))
    private Customer customer;
}
