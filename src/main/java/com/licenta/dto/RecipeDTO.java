package com.licenta.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeDTO {
     Long id;
     String label;
     String image;
     String url;
     int yield;
     double calories;
     double totalWeight;
     List<String> cuisineType;
     List<String> mealType;
     List<String> dishType;
     List<String> dietLabels;
     List<String> healthLabels;
     List<String> cautions;
     List<String> ingredientLines;
     List<IngredientDTO> ingredientDTOS;
     List<DigestDTO> digestDTO;
     String link;


     public RecipeDTO(String label, String image, String url, int yield, double calories, double totalWeight, List<String> cuisineType, List<String> mealType, List<String> dishType, List<String> dietLabels, List<String> healthLabels, List<String> cautions, List<String> ingredientLines, List<IngredientDTO> ingredientDTOS, List<DigestDTO> digestDTO, String link) {
          this.id = Long.valueOf(1);
          this.label = label;
          this.image = image;
          this.url = url;
          this.yield = yield;
          this.calories = calories;
          this.totalWeight = totalWeight;
          this.cuisineType = cuisineType;
          this.mealType = mealType;
          this.dishType = dishType;
          this.dietLabels = dietLabels;
          this.healthLabels = healthLabels;
          this.cautions = cautions;
          this.ingredientLines = ingredientLines;
          this.ingredientDTOS = ingredientDTOS;
          this.digestDTO = digestDTO;
          this.link = link;
     }

     public RecipeDTO(Long id, String label, String image, String url, int yield, double calories, double totalWeight, List<String> cuisineType, List<String> mealType, List<String> dishType, List<String> dietLabels, List<String> healthLabels, List<String> cautions, List<String> ingredientLines, List<IngredientDTO> ingredientDTOS, List<DigestDTO> digestDTO, String link) {
          this.id = id;
          this.label = label;
          this.image = image;
          this.url = url;
          this.yield = yield;
          this.calories = calories;
          this.totalWeight = totalWeight;
          this.cuisineType = cuisineType;
          this.mealType = mealType;
          this.dishType = dishType;
          this.dietLabels = dietLabels;
          this.healthLabels = healthLabels;
          this.cautions = cautions;
          this.ingredientLines = ingredientLines;
          this.ingredientDTOS = ingredientDTOS;
          this.digestDTO = digestDTO;
          this.link = link;
     }


}
