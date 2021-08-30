package com.licenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO implements Serializable {


    private UUID id;
    private String code;
    private Double metabolicEquivalent;
    private String category;
    private String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseDTO)) return false;

        ExerciseDTO that = (ExerciseDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (metabolicEquivalent != null ? !metabolicEquivalent.equals(that.metabolicEquivalent) : that.metabolicEquivalent != null)
            return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (metabolicEquivalent != null ? metabolicEquivalent.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
