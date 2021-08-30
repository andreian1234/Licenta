package com.licenta.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubDTO {
     String label;
     String tag;
     String schemaOrgTag;
     double total;
     boolean hasRDI;
     double daily;
     String unit;
}