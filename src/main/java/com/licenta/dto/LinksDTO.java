package com.licenta.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LinksDTO {
     NextDTO nextDTO;
     SelfDTO selfDTO;
}