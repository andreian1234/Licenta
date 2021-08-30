package com.licenta.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodEatenDTO {

    private UUID id;
    @JsonIgnore
    private UUID userId;
    private FoodDTO food;
    private Date date;
    private MeasureDTO measureDTO;
    private double quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodEatenDTO)) return false;

        FoodEatenDTO that = (FoodEatenDTO) o;

        if (Double.compare(that.quantity, quantity) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (food != null ? !food.equals(that.food) : that.food != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return measureDTO != null ? measureDTO.equals(that.measureDTO) : that.measureDTO == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (food != null ? food.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (measureDTO != null ? measureDTO.hashCode() : 0);
        temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
