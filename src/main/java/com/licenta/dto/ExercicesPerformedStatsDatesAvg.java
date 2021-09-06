package com.licenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ExercicesPerformedStatsDatesAvg {
    int caloriesAvg;
    int totalMinutesAvg;
    private List<ExercicesPerformedStats> exercicesPerformedStatsList;

    public ExercicesPerformedStatsDatesAvg() {
        this.setExercicesPerformedStatsList(new ArrayList<>());
    }
}


