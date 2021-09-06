package com.licenta.dto;

import com.licenta.models.ExercisePerformed;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ExercicesPerformedStats {
    private List<ExercisePerformed> exercisePerformedList;
    private int totalCalories;
    private int totalMinutes;
    private LocalDate localDate;

    public ExercicesPerformedStats() {
        this.totalCalories = 0;
        this.totalMinutes = 0;
        this.exercisePerformedList = new ArrayList<>();
        this.localDate = null;
    }
}
