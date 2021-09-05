package com.licenta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "food_eaten",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "food_id", "date"})
)
public final class FoodEaten {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "quantity", nullable = false)
    private Double quantity;


    @Override
    public String toString() {
        return super.toString();
    }

    public FoodEaten(
            final long id,
            final User user,
            final Food food,
            final LocalDate date,
            final double quantity
    ) {
        this.id = id;
        this.user = user;
        this.food = food;
        this.date = date;
        this.quantity = quantity;
    }


    public double getEnercKcal() {
        return food.getNutrients().getEnercKcal() * getRatio();
    }

    public double getProtein() {
        return food.getNutrients().getProtein() * getRatio();
    }

    public double getFat() {
        return food.getNutrients().getFat() * getRatio();
    }

    public double getCarbs() {
        return food.getNutrients().getCarbs() * getRatio();
    }

    public double getFiber() {
        return food.getNutrients().getFiber() * getRatio();
    }

    private double getRatio() {
        return quantity / food.getMeasure().getWeight();
    }
}
