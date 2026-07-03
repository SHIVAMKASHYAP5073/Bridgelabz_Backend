package com.greet.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * JPA Entity representing a registered application user.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    /** Role values: ROLE_USER, ROLE_ADMIN */
    private String role;

    /** Token used for password recovery flow */
    private String recoveryToken;
}
