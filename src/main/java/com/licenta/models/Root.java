package com.licenta.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Root{
    public int from;
    public int to;
    public int count;
    public Links _links;
    public List<Hit> hits;
}
