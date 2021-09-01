package com.licenta.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

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
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    public RecipeEaten(
            UUID id,
            User user,
            Recipe recipe,
            Date date,
            double quantity
    ) {
        this.id = Optional.ofNullable(id).orElse(UUID.randomUUID());
        this.user = user;
        this.recipe = recipe;
        this.date = date;
        this.quantity = quantity;
    }

    public double getCalories() {
        return recipe.getCalories() * getRatio();
    }

    public double getTotalWeight() {
        return recipe.getTotalWeight() * getRatio();
    }

    //TODO Vezi cum faci ca sa schimbi datele din digest


    private double getRatio() {
        return quantity / recipe.getTotalWeight();
    }
}
