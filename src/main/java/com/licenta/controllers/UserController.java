package com.licenta.controllers;

import com.licenta.dto.*;
import com.licenta.services.FoodService;
import com.licenta.services.HistoryService;
import com.licenta.services.RecipeService;
import com.licenta.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;
    private final HistoryService historyService;
    private final FoodService foodService;
    private final RecipeService recipeService;
    private String pattern = "yyyy-MM-dd";

    @GetMapping(path = "/food/stats/date")
    public final FoodStats getFoodStatsFromDate(@RequestParam String date,
                                                @RequestParam String mail) {
        return historyService.getFoodStatsForDate(userService.findUserByEmail(mail),
                LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern)));
    }

    @GetMapping(path = "/food/stats/dates")
    public final FoodStatsFromDateToDate getFoodStatsFromDates(@RequestParam String date,
                                                               @RequestParam String date1,
                                                               @RequestParam String mail) {

        return historyService.getFoodStatsFromDateToDate(userService.findUserByEmail(mail),
                LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern)),
                LocalDate.parse(date1, DateTimeFormatter.ofPattern(pattern)));
    }

    @GetMapping(path = "/food/stats/datesavg")
    public final FoodStatsFromDateToDate getFoodStatsFromDatesAvg(@RequestParam String date,
                                                                  @RequestParam String date1,
                                                                  @RequestParam String mail) {

        return historyService.getFoodStatsFromDateToDateAvg(userService.findUserByEmail(mail),
                LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern)),
                LocalDate.parse(date1, DateTimeFormatter.ofPattern(pattern)));
    }

    @GetMapping(path = "/exercice/stats/dates")
    public final ExercicesPerformedStats getExerciseStatsFromDates(@RequestParam String date1,
                                                                   @RequestParam String date2,
                                                                   @RequestParam String mail) {
        return historyService.getExerciseStatsFromDateToDate(userService.findUserByEmail(mail),
                LocalDate.parse(date1, DateTimeFormatter.ofPattern(pattern)),
                LocalDate.parse(date2, DateTimeFormatter.ofPattern(pattern)));

    }

    @GetMapping(path = "/exercice/stats/datesavg")
    public final ExercicesPerformedStatsDatesAvg getExerciseStatsFromDatesAvg(@RequestParam String date1,
                                                                              @RequestParam String date2,
                                                                              @RequestParam String mail) {
        return historyService.getExerciseStatsFromDateToDateAvg(userService.findUserByEmail(mail),
                LocalDate.parse(date1, DateTimeFormatter.ofPattern(pattern)),
                LocalDate.parse(date2, DateTimeFormatter.ofPattern(pattern)));

    }

    @GetMapping(path = "/stats/calories")
    public final StatsCalories getStatsCaloriesFromDate(@RequestParam String mail,
                                                        @RequestParam String date) {
        return historyService.getStatsCaloriesFromDate(userService.findUserByEmail(mail), LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern)));
    }


}
