package com.licenta.services;

import com.licenta.dto.FoodDTO;
import com.licenta.dto.FoodEatenDigest;
import com.licenta.dto.convertor.FoodDTOToFood;
import com.licenta.models.Food;
import com.licenta.models.FoodEaten;
import com.licenta.models.Nutrients;
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

        foodEaten.setFood(food);
        foodEaten.setDate(localDate);
        foodEaten.setQuantity(quantity);
        foodEaten.setUser(user);
        foodEatenRepository.save(foodEaten);
        return foodEaten;
    }

    public FoodEaten findById(Long id) {
        return foodEatenRepository.findById(id).get();
    }

    public FoodEatenDigest getNutrientsFromFoodEaten(Long id) {
        Nutrients nutrients = new Nutrients();
        FoodEaten foodEaten = foodEatenRepository.getById(id);
        nutrients.setCarbs(foodEaten.getCarbs());
        nutrients.setEnercKcal(foodEaten.getEnercKcal());
        nutrients.setFat(foodEaten.getFat());
        nutrients.setFiber(foodEaten.getFiber());
        nutrients.setProtein(foodEaten.getProtein());

        return new FoodEatenDigest(nutrients, foodEaten.getQuantity());
    }

    public FoodEatenDigest getNutrientsFromDate(User user, LocalDate localDate) {
        val foods = foodEatenRepository.findByUserEqualsAndDateEquals(user, localDate);
        FoodEatenDigest nutrients = new FoodEatenDigest();
        if (foods != null) {
            nutrients = getNutrientsFromFoodEaten(foods.get(0).getId());
            if (foods.size() > 1) {
                for (int i = 1; i < foods.size(); i++) {
                    nutrients.getNutrients().setProtein(nutrients.getNutrients().getProtein() + foods.get(i).getProtein());
                    nutrients.getNutrients().setFiber(nutrients.getNutrients().getFiber() + foods.get(i).getFiber());
                    nutrients.getNutrients().setCarbs(nutrients.getNutrients().getCarbs() + foods.get(i).getCarbs());
                    nutrients.getNutrients().setFat(nutrients.getNutrients().getFat() + foods.get(i).getFat());
                    nutrients.getNutrients().setEnercKcal(nutrients.getNutrients().getEnercKcal() + foods.get(i).getEnercKcal());
                    nutrients.setTotalWeight(nutrients.getTotalWeight() + foods.get(i).getQuantity());
                }
            }
        }
        return nutrients;


    }
}
