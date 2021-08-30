package com.licenta.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientDTO {
     String text;
     double weight;
     String foodCategory;
     String foodId;
     String image;
}