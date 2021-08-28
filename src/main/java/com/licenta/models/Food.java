package com.licenta.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Food{

     String foodId;
     String label;
     Nutrients nutrients;
     String category;
     String categoryLabel;
     String image;
     String foodContentsLabel;
     List<ServingSize> servingSizes;
     double servingsPerContainer;
}
