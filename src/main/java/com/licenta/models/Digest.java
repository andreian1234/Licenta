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
     String label;
     String tag;
     String schemaOrgTag;
     double total;
     boolean hasRDI;
     double daily;
     String unit;
     List<Sub> sub;
}