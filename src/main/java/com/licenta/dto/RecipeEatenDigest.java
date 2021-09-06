package com.licenta.dto;

import com.licenta.models.Digest;
import com.licenta.models.RecipeEaten;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

public class RecipeEatenDigest {

    private List<RecipeEaten> recipeEatenList;
    private List<Digest> digestList;
    private double calories;
    private double totalWeight;

    public RecipeEatenDigest() {
        this.calories = 0;
        this.totalWeight = 0;
        this.recipeEatenList = new ArrayList<>();
        this.digestList = new ArrayList<>();
    }
}

