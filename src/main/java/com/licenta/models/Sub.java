package com.licenta.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sub{
     String label;
     String tag;
     String schemaOrgTag;
     double total;
     boolean hasRDI;
     double daily;
     String unit;
}