package com.licenta.services;

import com.licenta.dto.ExercicesPerformedStats;
import com.licenta.models.Exercise;
import com.licenta.models.ExercisePerformed;
import com.licenta.models.User;
import com.licenta.repositories.ExerciseRepository;
import com.licenta.repositories.ExersicePerformedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExersicePerformedRepository exersicePerformedRepository;


    public List<Exercise> addExercises() {
        List<Exercise> exerciseList = new ArrayList<>();
//        exerciseList.add(new Exercise("bicycling", "bicycling, mountain, uphill, vigorous", 14.0));
//        exerciseList.add(new Exercise("bicycling", "bicycling, mountain, competitive, racing", 16.0));
//        exerciseList.add(new Exercise("bicycling", "bicycling, BMX", 7.5));
//        exerciseList.add(new Exercise("bicycling", "bicycling, general", 14.0));
//        exerciseList.add(new Exercise("dancing", "aerobic, general", 7.3));
//        exerciseList.add(new Exercise("fishing and hunting", "fishing, general", 3.5));
//        exerciseList.add(new Exercise("running", "jogging, general", 7.0));
//        exerciseList.add(new Exercise("running", "Running, 4km/h", 4.0));
//        exerciseList.add(new Exercise("running", "Running, 6km/h", 6.0));
//        exerciseList.add(new Exercise("running", "Running, 8km/h", 8.0));
//        exerciseList.add(new Exercise("running", "Running, 10km/h", 10.0));
//        exerciseList.add(new Exercise("running", "Running, 12km/h", 12.0));
//        exerciseList.add(new Exercise("running", "Running, 14km/h", 14.0));
//        exerciseList.add(new Exercise("running", "Running, 16km/h", 16.0));
//        exerciseList.add(new Exercise("sports", "basketball, general", 6.5));
//        exerciseList.add(new Exercise("sports", "bowling", 3.0));
//        exerciseList.add(new Exercise("sports", "boxing, in ring, general", 12.8));
//        exerciseList.add(new Exercise("sports", "boxing, sparring", 7.8));
//        exerciseList.add(new Exercise("sports", "handball, general", 12.0));
//        exerciseList.add(new Exercise("sports", "rock climbing, rappelling", 5.0));
//        exerciseList.add(new Exercise("sports", "rugby, union, team, competitive", 8.3));
//        exerciseList.add(new Exercise("sports", "football, competitive", 10.0));
//        exerciseList.add(new Exercise("sports", "football, casual", 8.0));
//        exerciseList.add(new Exercise("sports", "table tennis, ping pong", 4.0));
//        exerciseList.add(new Exercise("sports", "tennis, general", 7.3));
//        exerciseList.add(new Exercise("sports", "volleyball", 4.0));
//        exerciseList.add(new Exercise("sports", "volleyball, competitive, in gymnasium", 6.0));
//        exerciseList.add(new Exercise("walking", "walking for pleasure", 3.5));
//        exerciseList.add(new Exercise("water activities", "swimming laps, freestyle, fast, vigorous effort", 9.8));
//        exerciseList.add(new Exercise("water activities", "swimming, butterfly, general", 13.8));
//        exerciseList.add(new Exercise("water activities", "water polo", 10.0));
//        exerciseList.add(new Exercise("winter activities", "skating, ice, general", 7.0));
//        exerciseList.add(new Exercise("winter activities", "skiing, general", 7.0));
//        exerciseList.add(new Exercise("winter activities", "skiing, downhill, alpine or snowboarding, moderate effort, general, active time only", 7.0));
        return exerciseRepository.saveAll(exerciseList);


    }

    public ExercisePerformed addExercisePerformed(User user, Long id, int minutes) {
        int calories = (int) (exerciseRepository.findById(id).get().getMetabolicEquivalent() * 3.5 * user.getWeight() * minutes / 200);
        ExercisePerformed exercisePerformed = new ExercisePerformed(user, exerciseRepository.findById(id).get(), LocalDate.of(2021, 9, 7), minutes, calories);
        return exersicePerformedRepository.save(exercisePerformed);
    }

    public ExercicesPerformedStats getStatsDate(User userByEmail, LocalDate date) {
        ExercicesPerformedStats exercicesPerformedStats = new ExercicesPerformedStats();
        List<ExercisePerformed> exercisePerformedList = exersicePerformedRepository.findByDateEqualsOrderByDate(date);
        if (!exercisePerformedList.isEmpty()) {
            for (ExercisePerformed exercisePerformed : exercisePerformedList) {
                exercicesPerformedStats.getExercisePerformedList().add(exercisePerformed);
                exercicesPerformedStats.setTotalCalories(exercicesPerformedStats.getTotalCalories() + exercisePerformed.getCaloriesBurned());
                exercicesPerformedStats.setTotalMinutes(exercicesPerformedStats.getTotalMinutes() + exercisePerformed.getMinutes());
            }
        }
        exercicesPerformedStats.setLocalDate(date);
        return exercicesPerformedStats;
    }
}
