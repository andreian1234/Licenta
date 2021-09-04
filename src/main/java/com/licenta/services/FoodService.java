package com.licenta.services;

import com.licenta.dto.FoodDTO;
import com.licenta.dto.convertor.FoodDTOToFood;
import com.licenta.models.Food;
import com.licenta.repositories.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodDTOToFood foodDTOToFood;


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

}
