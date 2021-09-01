package com.licenta.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "exercise")
public final class Exercise {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "code", length = 5, nullable = false)
    private String code;

    @Column(name = "metabolic_equivalent", nullable = false)
    private Double metabolicEquivalent;

    @Column(name = "category", length = 25, nullable = false)
    private String category;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    public Exercise(
            final UUID id,
            final String code,
            final double metabolicEquivalent,
            final String category,
            final String description
    ) {
        this.id = Optional.ofNullable(id).orElse(UUID.randomUUID());
        this.code = code;
        this.metabolicEquivalent = metabolicEquivalent;
        this.category = category;
        this.description = description;
    }
}

