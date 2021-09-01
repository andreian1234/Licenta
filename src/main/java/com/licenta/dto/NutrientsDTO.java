package com.licenta.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor

public class NutrientsDTO {

    double enercKcal;
    double protein;
    double fat;
    double carbs;
    double fiber;

    public NutrientsDTO(double enercKcal, double protein, double fat, double carbs, double fiber) {
        this.enercKcal = enercKcal;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.fiber = fiber;
    }
}