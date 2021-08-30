package com.licenta.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeDTO {
     String label;
     String image;
     String url;
     int yield;
     List<String> dietLabels;
     List<String> healthLabels;
     List<String> cautions;
     List<String> ingredientLines;
     List<IngredientDTO> ingredientDTOS;
     double calories;
     double totalWeight;
     List<String> cuisineType;
     List<String> mealType;
     List<String> dishType;
     List<DigestDTO> digestDTO;
     String self;
}
