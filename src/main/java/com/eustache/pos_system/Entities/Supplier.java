package com.eustache.pos_system.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {
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
            length = 100
    )
    private String address;
    @Column(
            nullable = false,
            length = 100
    )
    private String phone;
    @Column(
            nullable = false,
            length = 100
    )
    private String email;

    /**
     * One supplier can have many products
     */
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
