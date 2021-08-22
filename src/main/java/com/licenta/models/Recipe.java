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
    public String uri;
    public String label;
    public String image;
    public String source;
    public String url;
    public String shareAs;
    public int yield;
    public List<String> dietLabels;
    public List<String> healthLabels;
    public List<String> cautions;
    public List<String> ingredientLines;
    public List<Ingredient> ingredients;
    public double calories;
    public double totalWeight;
    public int totalTime;
    public List<String> cuisineType;
    public List<String> mealType;
    public List<String> dishType;
    public List<Digest> digest;
}
