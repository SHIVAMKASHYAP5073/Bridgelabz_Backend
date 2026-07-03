package com.greet.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * JPA Entity representing a Tag/Label that can be attached to greetings.
 */
@Entity
@Table(name = "tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}
