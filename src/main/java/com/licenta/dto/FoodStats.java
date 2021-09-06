package com.licenta.dto;

import com.licenta.models.Digest;
import com.licenta.models.FoodEaten;
import com.licenta.models.RecipeEaten;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class FoodStats {

    private List<FoodEaten> foodEatenList;
    private List<RecipeEaten> recipeEatenList;
    private List<Digest> digestList;
    private double calories;
    private double totalWeight;
    private LocalDate localDate;


    public FoodStats() {
        this.calories = 0;
        this.foodEatenList = new ArrayList<>();
        this.totalWeight = 0;
        this.recipeEatenList = new ArrayList<>();
        this.digestList = new ArrayList<>();
        this.localDate = null;
    }
}
