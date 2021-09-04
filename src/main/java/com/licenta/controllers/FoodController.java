package com.licenta.controllers;

import com.licenta.dto.FoodDTO;
import com.licenta.dto.HintDTO;
import com.licenta.dto.RootFoodDTO;
import com.licenta.models.Food;
import com.licenta.repositories.FoodRepository;
import com.licenta.services.FoodApiService;
import com.licenta.services.FoodService;
import com.licenta.services.URLService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("foods")
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodService foodService;


    @GetMapping("/find")
    public List<Food> getAllFoods() {
        return this.foodRepository.findAll();
    }

    @SneakyThrows
    @GetMapping("/addToDatabase/{ingredient}")
    public final void addFoodToDB(@PathVariable final String ingredient) {

        RootFoodDTO rootFoodDTO = FoodApiService.getFoodRootDetailsObject(URLService.getApi(URLService.getFoodUrl(ingredient)));
        List<FoodDTO> foodDTOList = new ArrayList<>();
        for (HintDTO hintDTO : rootFoodDTO.getHintDTOS()) {
            foodDTOList.add(hintDTO.getFoodDTO());
            foodService.saveFoodDTOList(foodDTOList);
        }

        for (int i = 0; i < 4; i++) {
            String link = rootFoodDTO.getLinksDTO().getNextDTO().getHref();
            rootFoodDTO = FoodApiService.getFoodRootDetailsObject(URLService.getApi(link));
            List<FoodDTO> foodDTOList1 = new ArrayList<>();
            for (HintDTO hintDTO : rootFoodDTO.getHintDTOS())
                foodDTOList1.add(hintDTO.getFoodDTO());
            foodService.saveFoodDTOList(foodDTOList1);
            System.out.println("Esti Aici !!!!!!!!!!!!!!!!!!!!!!!!!!" + i);
        }
    }

}
