package com.licenta.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RootRecipeDTO {
     int from;
     int to;
     int count;
     LinksDTO _linksDTO;
     List<HitDTO> hitDTOS;
}
