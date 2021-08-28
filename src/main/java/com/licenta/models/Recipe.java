package com.licenta.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Recipe{
     String uri;
     String label;
     String image;
     String source;
     String url;
     String shareAs;
     int yield;
     List<String> dietLabels;
     List<String> healthLabels;
     List<String> cautions;
     List<String> ingredientLines;
     List<Ingredient> ingredients;
     double calories;
     double totalWeight;
     int totalTime;
     List<String> cuisineType;
     List<String> mealType;
     List<String> dishType;
     List<Digest> digest;
}
