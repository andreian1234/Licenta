package com.licenta.dto;

import com.licenta.models.Nutrients;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodEatenDigest {

    Nutrients nutrients;
    double totalWeight;
}
