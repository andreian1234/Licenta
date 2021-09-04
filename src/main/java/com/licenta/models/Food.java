package com.licenta.models;


import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Table(
        name = "food",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id"})
)
public class Food {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "foodId")
    private String foodId;

    @Embedded
    private Nutrients nutrients;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "categoryLabel", nullable = false)
    private String categoryLabel;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "label")
    private String label;

    @Embedded
    private Measure measure;

    public Food(
            final String foodId,
            final String label,
            final Nutrients nutrients,
            final String category,
            final String categoryLabel,
            final String image,
            final Measure measure
    ) {
        this.foodId = foodId;
        this.nutrients = nutrients;
        this.category = category;
        this.categoryLabel = categoryLabel;
        this.image = image;
        this.label = label;
        this.measure = measure;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Nutrients getNutrients() {
        return nutrients;
    }

    public void setNutrients(Nutrients nutrients) {
        this.nutrients = nutrients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
