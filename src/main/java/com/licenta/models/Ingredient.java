package com.licenta.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ingredient{
    public String text;
    public double weight;
    public String foodCategory;
    public String foodId;
    public String image;
}