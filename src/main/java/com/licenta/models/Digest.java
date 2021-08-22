package com.licenta.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Digest{
    public String label;
    public String tag;
    public String schemaOrgTag;
    public double total;
    public boolean hasRDI;
    public double daily;
    public String unit;
    public List<Sub> sub;
}