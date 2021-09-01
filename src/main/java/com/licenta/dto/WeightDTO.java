package com.licenta.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightDTO {

    private UUID id;
    private UUID userId;
    private Date date;
    private double pounds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeightDTO)) return false;

        WeightDTO weightDTO = (WeightDTO) o;

        if (Double.compare(weightDTO.pounds, pounds) != 0) return false;
        if (id != null ? !id.equals(weightDTO.id) : weightDTO.id != null) return false;
        if (userId != null ? !userId.equals(weightDTO.userId) : weightDTO.userId != null) return false;
        return date != null ? date.equals(weightDTO.date) : weightDTO.date == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(pounds);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
