package com.licenta.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "exercise")
public final class Exercise {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "metabolic_equivalent", nullable = false)
    private Double metabolicEquivalent;

    @Column(name = "category", length = 25, nullable = false)
    private String category;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    public Exercise(
            final Long id,
            final double metabolicEquivalent,
            final String category,
            final String description
    ) {
        this.id = id;
        this.metabolicEquivalent = metabolicEquivalent;
        this.category = category;
        this.description = description;
    }

    public Exercise(
            final String category,
            final String description,
            final double metabolicEquivalent
    ) {
        this.metabolicEquivalent = metabolicEquivalent;
        this.category = category;
        this.description = description;
    }
}

