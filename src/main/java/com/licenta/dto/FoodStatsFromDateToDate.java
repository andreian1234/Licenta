package com.licenta.dto;

import com.licenta.models.Digest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class FoodStatsFromDateToDate {
    private List<FoodStats> foodStatsList;
    private List<Digest> digestList;
    private int totalCalories;
    private int totalWeight;

    public FoodStatsFromDateToDate() {
        this.foodStatsList = new ArrayList<>();
        this.digestList = new ArrayList<>();
        this.totalCalories = 0;
        this.totalWeight = 0;
    }
}


