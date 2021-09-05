package com.licenta.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "recipe_eaten",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "recipe_id", "date"})
)
public class RecipeEaten {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    public RecipeEaten(
            long id,
            User user,
            Recipe recipe,
            LocalDate date,
            double quantity
    ) {
        this.id = id;
        this.user = user;
        this.recipe = recipe;
        this.date = date;
        this.quantity = quantity;
    }

    //TODO Vezi cum faci ca sa schimbi datele din digest


    public double getRatio() {
        return quantity / recipe.getTotalWeight();
    }
}
