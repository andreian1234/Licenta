package com.licenta.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodEatenDTO {

    private long id;
    @JsonIgnore
    private long userId;
    private String foodId;
    private FoodDTO food;
    private LocalDate date;
    private double quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodEatenDTO)) return false;

        FoodEatenDTO that = (FoodEatenDTO) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (Double.compare(that.quantity, quantity) != 0) return false;
        if (food != null ? !food.equals(that.food) : that.food != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (food != null ? food.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
