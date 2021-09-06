package com.licenta.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodDTO implements Serializable {

     String foodId;
     String label;
     NutrientsDTO nutrientsDTO;
     String category;
     String categoryLabel;
     String image;
     MeasureDTO measureDTO;

     @Override
     public boolean equals(Object o) {

          if (this == o) return true;
          if (!(o instanceof FoodDTO)) return false;

          FoodDTO foodDTO = (FoodDTO) o;

          if (foodId != null ? !foodId.equals(foodDTO.foodId) : foodDTO.foodId != null) return false;
          if (label != null ? !label.equals(foodDTO.label) : foodDTO.label != null) return false;
          if (nutrientsDTO != null ? !nutrientsDTO.equals(foodDTO.nutrientsDTO) : foodDTO.nutrientsDTO != null)
               return false;
          if (category != null ? !category.equals(foodDTO.category) : foodDTO.category != null) return false;
          if (categoryLabel != null ? !categoryLabel.equals(foodDTO.categoryLabel) : foodDTO.categoryLabel != null)
               return false;
          if (image != null ? !image.equals(foodDTO.image) : foodDTO.image != null) return false;
          return measureDTO != null ? measureDTO.equals(foodDTO.measureDTO) : foodDTO.measureDTO == null;
     }

     @Override
     public int hashCode() {
          int result = foodId != null ? foodId.hashCode() : 0;
          result = 31 * result + (label != null ? label.hashCode() : 0);
          result = 31 * result + (nutrientsDTO != null ? nutrientsDTO.hashCode() : 0);
          result = 31 * result + (category != null ? category.hashCode() : 0);
          result = 31 * result + (categoryLabel != null ? categoryLabel.hashCode() : 0);
         result = 31 * result + (image != null ? image.hashCode() : 0);
         result = 31 * result + (measureDTO != null ? measureDTO.hashCode() : 0);
          return result;
     }
}

