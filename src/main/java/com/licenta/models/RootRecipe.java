package com.licenta.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RootRecipe {
     int from;
     int to;
     int count;
     Links _links;
     List<Hit> hits;
}
