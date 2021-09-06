package com.licenta.controllers;

import com.licenta.dto.ExercicesPerformedStats;
import com.licenta.models.Exercise;
import com.licenta.models.ExercisePerformed;
import com.licenta.models.User;
import com.licenta.services.ExerciseService;
import com.licenta.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("exercices")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final UserService userService;

    @PostMapping(path = "/addExercises")
    public List<Exercise> addExercises() {
        return exerciseService.addExercises();
    }

    @PostMapping(path = "/addPerformed")
    public ExercisePerformed addExercisePerformed(
            @RequestParam String mail, @RequestParam Long id, @RequestParam int minutes
    ) {
        User user = userService.findUserByEmail(mail);
        return exerciseService.addExercisePerformed(user, id, minutes);
    }

    @GetMapping(path = "/stats/date")
    public ExercicesPerformedStats getStatsFromDate(
            @RequestParam String date,
            @RequestParam String mail
    ) {
        return exerciseService.getStatsDate(userService.findUserByEmail(mail), LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
