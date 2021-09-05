package com.licenta.dto;

import com.licenta.models.Digest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeEatenDigest {

    private Long recipeEatenId;
    private List<Digest> digestList;
    private double calories;
    private double totalWeight;
}
