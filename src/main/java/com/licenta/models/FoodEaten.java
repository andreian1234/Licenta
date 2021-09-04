package com.licenta.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor

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


    @Column(name = "identificaton")
    private String foodId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "quantity", nullable = false)
    private Double quantity;


    public FoodEaten(
            final long id,
            final String foodId,
            final User user,
            final Food food,
            final LocalDate date,
            final double quantity
    ) {
        this.id = id;
        this.foodId = foodId;
        this.user = user;
        this.food = food;
        this.date = date;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
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
