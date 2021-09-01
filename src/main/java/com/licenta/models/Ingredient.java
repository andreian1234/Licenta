package com.licenta.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Ingredient {

    @Column(name = "text")
    String text;

    @Column(name = "weight")
    double weight;

    @Column(name = "foodCategory")
    String foodCategory;

    @Column(name = "foodId")
    String foodId;

    @Column(name = "image")
    String image;
}
