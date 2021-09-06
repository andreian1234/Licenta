package com.licenta.services;

import com.licenta.dto.*;
import com.licenta.models.Digest;
import com.licenta.models.User;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class HistoryService {

    private final FoodService foodService;
    private final RecipeService recipeService;
    private final ExerciseService exerciseService;


    public FoodStats getFoodStatsForDate(User user, LocalDate localDate) {
        val nutrients = foodService.getNutrientsFromDate(user, localDate);
        val digest = recipeService.getDigestFromDate(user, localDate);
        FoodStats foodStats = new FoodStats();
        foodStats.setFoodEatenList(nutrients.getFoodEatenList());
        foodStats.setRecipeEatenList(digest.getRecipeEatenList());


        digest.getDigestList().get(0).setDaily((digest.getDigestList().get(0).getTotal() + nutrients.getNutrients().getFat()) /
                digest.getDigestList().get(0).getTotal() * digest.getDigestList().get(0).getDaily());

        digest.getDigestList().get(0).setTotal(
                digest.getDigestList().get(0).getTotal() + nutrients.getNutrients().getFat()
        );


        digest.getDigestList().get(1).setDaily((digest.getDigestList().get(1).getTotal() + nutrients.getNutrients().getCarbs()) /
                digest.getDigestList().get(1).getTotal() * digest.getDigestList().get(1).getDaily());

        digest.getDigestList().get(1).setTotal(
                digest.getDigestList().get(1).getTotal() + nutrients.getNutrients().getCarbs()
        );

        digest.getDigestList().get(2).setDaily((digest.getDigestList().get(2).getTotal() + nutrients.getNutrients().getProtein()) /
                digest.getDigestList().get(2).getTotal() * digest.getDigestList().get(2).getDaily());

        digest.getDigestList().get(2).setTotal(
                digest.getDigestList().get(2).getTotal() + nutrients.getNutrients().getProtein()
        );

        digest.setCalories(digest.getCalories() + nutrients.getNutrients().getEnercKcal());
        digest.setTotalWeight(digest.getTotalWeight() + nutrients.getTotalWeight());

        foodStats.setTotalWeight(digest.getTotalWeight());
        foodStats.setCalories(digest.getCalories());
        foodStats.setDigestList(digest.getDigestList());
        foodStats.setLocalDate(localDate);
        return foodStats;
    }

    public FoodStatsFromDateToDate getFoodStatsFromDateToDate(User userByEmail, LocalDate parse, LocalDate parse1) {
        FoodStatsFromDateToDate foodStatsFromDateToDate = new FoodStatsFromDateToDate();
        FoodStats foodStats = new FoodStats();
        List<FoodStats> foodStatsList = new ArrayList<>();
        LocalDate parse2 = parse;
        for (int i = 0; i <= Period.between(parse, parse1).getDays(); i++) {
            foodStatsList.add(getFoodStatsForDate(userByEmail, parse2));
            parse2 = parse2.plusDays(1);
        }
        foodStatsFromDateToDate.setFoodStatsList(foodStatsList);
        int inceput = -1;
        for (int i = 0; i < foodStatsList.size(); i++) {
            if (foodStatsList.get(i).getTotalWeight() != 0) {
                foodStats = foodStatsList.get(i);
                inceput = i;
                break;
            }
        }
        if ((inceput != foodStatsList.size() - 1) && inceput != -1)
            for (int i = inceput + 1; i < foodStatsList.size(); i++) {
                for (int j = 0; j < 26; j++) {
                    foodStats.getDigestList().get(j).setDaily(foodStats.getDigestList().get(j).getDaily()
                            + foodStatsList.get(i).getDigestList().get(j).getDaily());
                    foodStats.getDigestList().get(j).setTotal(foodStats.getDigestList().get(j).getTotal()
                            + foodStatsList.get(i).getDigestList().get(j).getTotal());
                }

                foodStats.setTotalWeight(foodStats.getTotalWeight() + foodStatsList.get(i).getTotalWeight());
                foodStats.setCalories(foodStats.getCalories() + foodStatsList.get(i).getCalories());
            }
        foodStatsFromDateToDate.setDigestList(foodStats.getDigestList());
        foodStatsFromDateToDate.setTotalCalories((int) foodStats.getCalories());
        foodStatsFromDateToDate.setTotalWeight((int) foodStats.getTotalWeight());

        return foodStatsFromDateToDate;
    }


    public FoodStatsFromDateToDate getFoodStatsFromDateToDateAvg(User userByEmail, LocalDate date1, LocalDate date2) {
        FoodStatsFromDateToDate foodStatsFromDateToDate = getFoodStatsFromDateToDate(userByEmail, date1, date2);
        val period = Period.between(date1, date2).getDays() + 1;
        foodStatsFromDateToDate.setTotalWeight(foodStatsFromDateToDate.getTotalWeight() / period);
        foodStatsFromDateToDate.setTotalCalories(foodStatsFromDateToDate.getTotalCalories() / period);

        for (Digest digest : foodStatsFromDateToDate.getDigestList()) {
            digest.setTotal(digest.getTotal() / period);
            digest.setDaily(digest.getDaily() / period);
        }

        return foodStatsFromDateToDate;

    }

    public ExercicesPerformedStats getExerciseStatsFromDateToDate(User userByEmail, LocalDate date1, LocalDate date2) {
        List<ExercicesPerformedStats> exercicesPerformedStatsList = new ArrayList<>();
        ExercicesPerformedStats exercicesPerformedStats = new ExercicesPerformedStats();

        LocalDate date = date1;

        for (int i = 0; i <= Period.between(date1, date2).getDays(); i++) {
            exercicesPerformedStatsList.add(exerciseService.getStatsDate(userByEmail, date));
            date = date.plusDays(1);
        }

        int inceput = -1;
        for (int i = 0; i < exercicesPerformedStatsList.size(); i++) {
            if (exercicesPerformedStatsList.get(i).getTotalCalories() != 0) {
                exercicesPerformedStats = exercicesPerformedStatsList.get(i);
                inceput = i;
                break;
            }
        }
        if (inceput != (exercicesPerformedStatsList.size() - 1) && inceput != -1)
            for (int i = inceput + 1; i < exercicesPerformedStatsList.size(); i++) {
                for (int j = 0; j < exercicesPerformedStatsList.get(i).getExercisePerformedList().size(); j++) {
                    exercicesPerformedStats.getExercisePerformedList().add(exercicesPerformedStatsList.get(i).getExercisePerformedList().get(j));
                }
                exercicesPerformedStats.setTotalCalories(exercicesPerformedStats.getTotalCalories() + exercicesPerformedStatsList.get(i).getTotalCalories());
                exercicesPerformedStats.setTotalMinutes(exercicesPerformedStats.getTotalMinutes() + exercicesPerformedStatsList.get(i).getTotalMinutes());
            }


        return exercicesPerformedStats;
    }

    public ExercicesPerformedStatsDatesAvg getExerciseStatsFromDateToDateAvg(User userByEmail, LocalDate date1, LocalDate date2) {
        List<ExercicesPerformedStats> exercicesPerformedStatsList = new ArrayList<>();
        ExercicesPerformedStatsDatesAvg exercicesPerformedStatsDatesAvg = new ExercicesPerformedStatsDatesAvg();

        LocalDate date = date1;

        for (int i = 0; i <= Period.between(date1, date2).getDays(); i++) {
            exercicesPerformedStatsList.add(exerciseService.getStatsDate(userByEmail, date));
            date = date.plusDays(1);
        }

        double calories = 0;
        double time = 0;
        for (ExercicesPerformedStats exercicesPerformedStats : exercicesPerformedStatsList) {
            exercicesPerformedStatsDatesAvg.getExercicesPerformedStatsList().add(exercicesPerformedStats);
            calories += exercicesPerformedStats.getTotalCalories();
            time += exercicesPerformedStats.getTotalMinutes();
        }
        exercicesPerformedStatsDatesAvg.setCaloriesAvg((int) (calories / exercicesPerformedStatsList.size()));
        exercicesPerformedStatsDatesAvg.setTotalMinutesAvg((int) (time / exercicesPerformedStatsList.size()));
        return exercicesPerformedStatsDatesAvg;
    }

    public StatsCalories getStatsCaloriesFromDate(User userByEmail, LocalDate date) {

        val foodEaten = getFoodStatsForDate(userByEmail, date);
        val exercicesPerformed = exerciseService.getStatsDate(userByEmail, date);
        StatsCalories statsCalories = new StatsCalories();
        statsCalories.setCaloriesFromFood((int) foodEaten.getCalories());
        statsCalories.setCaloriesBurned(exercicesPerformed.getTotalCalories());
        statsCalories.setCaloriesAllowed((int) userByEmail.getMaintenanceCalories());
        statsCalories.setTotalCaloriesRemained(statsCalories.getCaloriesAllowed() - statsCalories.getCaloriesFromFood() + statsCalories.getCaloriesBurned());
        return statsCalories;
    }
}
