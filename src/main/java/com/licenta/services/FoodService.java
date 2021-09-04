package com.licenta.services;

import com.licenta.dto.FoodDTO;
import com.licenta.dto.convertor.FoodDTOToFood;
import com.licenta.models.Food;
import com.licenta.models.FoodEaten;
import com.licenta.models.User;
import com.licenta.repositories.FoodEatenRepository;
import com.licenta.repositories.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodDTOToFood foodDTOToFood;
    private final FoodEatenRepository foodEatenRepository;


    public List<Food> saveFoodDTOList(List<FoodDTO> foodDTOList) {
        List<Food> foodList = new ArrayList<>();
        for (val foodDTO : foodDTOList) {
            foodList.add(foodDTOToFood.convert(foodDTO));
        }
        foodRepository.saveAll(foodList);
        return foodList;
    }

    public List<Food> searchFoods() {
        return foodRepository.findAll();
    }

    public Food searchById(Long id) {
        return foodRepository.findById(id).get();
    }

    public FoodEaten saveOneFoodEaten(User user, Food food, LocalDate localDate, double quantity) {
        FoodEaten foodEaten = new FoodEaten();

        foodEaten.setFoodId(food.getFoodId());
        foodEaten.setFood(food);
        foodEaten.setDate(localDate);
        foodEaten.setQuantity(quantity);
        foodEaten.setUser(user);
        foodEatenRepository.save(foodEaten);
        return foodEaten;
    }

}
