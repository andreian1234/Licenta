package com.licenta.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightDTO {

    private long id;
    private long userId;
    private Date date;
    private double pounds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeightDTO)) return false;

        WeightDTO weightDTO = (WeightDTO) o;

        if (id != weightDTO.id) return false;
        if (userId != weightDTO.userId) return false;
        if (Double.compare(weightDTO.pounds, pounds) != 0) return false;
        return date != null ? date.equals(weightDTO.date) : weightDTO.date == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(pounds);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
