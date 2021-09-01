package com.licenta.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Nutrients {

    @Column(name = "enercKcal")
    double enercKcal;

    @Column(name = "protein")
    double protein;

    @Column(name = "fat")
    double fat;

    @Column(name = "carbs")
    double carbs;

    @Column(name = "fiber")
    double fiber;

    public double getEnercKcal() {
        return enercKcal;
    }

    public void setEnercKcal(double enercKcal) {
        this.enercKcal = enercKcal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }


}
