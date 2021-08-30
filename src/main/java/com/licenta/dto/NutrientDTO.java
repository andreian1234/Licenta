package com.licenta.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NutrientDTO {
     String label;
     double quantity;
     String unit;
}
