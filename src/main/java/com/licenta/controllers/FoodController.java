package com.licenta.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("foods")
public class FoodController {

//    @Autowired
//    private FoodRepository foodRepository;
//
//    @GetMapping("/find")
//    public List<Food> getAllFoods() {
//        return this.foodRepository.findAll();
//    }
//
//    @PostMapping("/create")
//    public Food createFood(@RequestBody Food food) {
//        return this.foodRepository.save(food);
//    }

}
