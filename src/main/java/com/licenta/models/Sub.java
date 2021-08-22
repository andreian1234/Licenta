package com.licenta.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sub{
    public String label;
    public String tag;
    public String schemaOrgTag;
    public double total;
    public boolean hasRDI;
    public double daily;
    public String unit;
}