package com.licenta.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Measure {

    @Column(name = "measureLabel")
    private String label;

    @Column(name = "weight")
    private double weight;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
