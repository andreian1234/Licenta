package com.licenta.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercisePerformedDTO implements Serializable {


    private Long id;
    @JsonIgnore
    private Long userId;
    private ExerciseDTO exercise;
    private LocalDate date;
    private int minutes;
    private int caloriesBurned;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExercisePerformedDTO)) return false;

        ExercisePerformedDTO that = (ExercisePerformedDTO) o;

        if (minutes != that.minutes) return false;
        if (caloriesBurned != that.caloriesBurned) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (exercise != null ? !exercise.equals(that.exercise) : that.exercise != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (exercise != null ? exercise.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + minutes;
        result = 31 * result + caloriesBurned;
        return result;
    }
}
