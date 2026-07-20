package com.eustache.pos_system.Entities;

import com.eustache.pos_system.Helpers.RoleEnum;
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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false,
            length = 100
    )
    private String username;
    @Column(
            nullable = false,
            length = 100
    )
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 100
    )
    private RoleEnum role;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales;
}
