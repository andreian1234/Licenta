package com.licenta.dto;

import com.licenta.models.FoodEaten;
import com.licenta.models.Nutrients;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class FoodEatenDigest {

    private List<FoodEaten> foodEatenList;
    private Nutrients nutrients;
    private double totalWeight;

    public FoodEatenDigest() {
        this.setTotalWeight(0);
        this.setNutrients(new Nutrients(0, 0, 0, 0, 0));
        this.foodEatenList = new ArrayList<>();
    }
}
