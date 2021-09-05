package com.licenta.controllers;

import com.licenta.dto.RecipeEatenDigest;
import com.licenta.dto.UserDTO;
import com.licenta.models.User;
import com.licenta.services.FoodService;
import com.licenta.services.HistoryService;
import com.licenta.services.RecipeService;
import com.licenta.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/stats/{date}")
    public final RecipeEatenDigest getStatsFromDate(@PathVariable String date) {
        return historyService.getTotalDigestFromDate(userService.findUserByEmail("andrei@gmail.com"),
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @GetMapping(path = "/stats/dates")
    public final RecipeEatenDigest getStatsFromDate(@RequestParam String date, @RequestParam String date1) {
        return historyService.getTotalDigestFromDateToDate(userService.findUserByEmail("andrei@gmail.com"),
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @PostMapping(path = "/signup")
    public final User saveUser(
            @RequestBody final UserDTO userDTO
    ) {
        return userService.createUser(userDTO);

    }
}
