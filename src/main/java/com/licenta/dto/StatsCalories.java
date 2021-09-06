package com.licenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatsCalories {
    private int caloriesAllowed;
    private int caloriesFromFood;
    private int caloriesBurned;
    private int totalCaloriesRemained;


}
