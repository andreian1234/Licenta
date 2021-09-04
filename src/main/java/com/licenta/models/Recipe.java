package com.licenta.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "recipe",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id"})
)
public final class Recipe {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "label")
    private String label;

    @Column(name = "image")
    private String image;

    @Column(name = "url")
    private String url;

    @Column(name = "yield")
    private int yield;

    @Column(name = "calories")
    private double calories;

    @Column(name = "totalWeight")
    private double totalWeight;

    @Column(name = "self")
    private String self;

    @ElementCollection
    private List<String> dietLabelsList = new ArrayList<>();

    @ElementCollection
    private List<String> healthLabelsList = new ArrayList<>();

    @ElementCollection
    private List<String> cautionsList = new ArrayList<>();

    @ElementCollection
    private List<String> ingredientLinesList = new ArrayList<>();

    @ElementCollection
    private List<String> cuisineTypeList = new ArrayList<>();

    @ElementCollection
    private List<String> mealTypeList = new ArrayList<>();

    @ElementCollection
    private List<String> dishTypeList = new ArrayList<>();

    @ElementCollection
    private List<Ingredient> ingredientList = new ArrayList<>();

    @ElementCollection
    private List<Digest> digestList = new ArrayList<>();

    public Recipe(
            String label,
            String image,
            String url,
            int yield,
            double calories,
            double totalWeight,
            String self,
            List<String> dietLabelsList,
            List<String> healthLabelsList,
            List<String> cautionsList,
            List<String> ingredientLinesList,
            List<String> cuisineTypeList,
            List<String> mealTypeList,
            List<String> dishTypeList,
            List<Ingredient> ingredientList,
            List<Digest> digestList) {
        this.label = label;
        this.image = image;
        this.url = url;
        this.yield = yield;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.self = self;
        this.dietLabelsList = dietLabelsList;
        this.healthLabelsList = healthLabelsList;
        this.cautionsList = cautionsList;
        this.ingredientLinesList = ingredientLinesList;
        this.cuisineTypeList = cuisineTypeList;
        this.mealTypeList = mealTypeList;
        this.dishTypeList = dishTypeList;
        this.ingredientList = ingredientList;
        this.digestList = digestList;
    }
}
