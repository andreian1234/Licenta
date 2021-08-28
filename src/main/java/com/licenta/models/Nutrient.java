package com.licenta.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Nutrient {
     String label;
     double quantity;
     String unit;
}
