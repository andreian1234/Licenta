package com.licenta.controllers;

import com.licenta.models.Food;
import com.licenta.repositories.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("foods")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("/find")
    public List<Food> getAllFoods() {
        return this.foodRepository.findAll();
    }

    @PostMapping("/create")
    public Food createFood(@RequestBody Food food) {
        return this.foodRepository.save(food);
    }

}
