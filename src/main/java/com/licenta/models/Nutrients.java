package com.licenta.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Nutrients {

    double ENERC_KCAL;
    double PROCNT;
    double FAT;
    double CHOCDF;
    double FIBTG;

    public Nutrients(double asDouble, double asDouble1, double asDouble2, double asDouble3, double asDouble4) {
    }
}