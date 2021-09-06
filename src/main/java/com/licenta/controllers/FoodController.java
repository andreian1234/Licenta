package com.licenta.controllers;

import com.licenta.dto.FoodDTO;
import com.licenta.dto.FoodEatenDigest;
import com.licenta.dto.HintDTO;
import com.licenta.dto.RootFoodDTO;
import com.licenta.models.Food;
import com.licenta.models.FoodEaten;
import com.licenta.models.User;
import com.licenta.repositories.FoodRepository;
import com.licenta.services.FoodApiService;
import com.licenta.services.FoodService;
import com.licenta.services.URLService;
import com.licenta.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("foods")
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodService foodService;
    private final UserService userService;


    @GetMapping("/find")
    public List<Food> getAllFoods() {
        return foodService.searchFoods();
    }


    @SneakyThrows
    @GetMapping("/search/{ingredient}")
    public final List<FoodDTO> addFoodToDB(@PathVariable final String ingredient) {


        RootFoodDTO rootFoodDTO = FoodApiService.getFoodRootDetailsObject(URLService.getApi(URLService.getFoodUrl(ingredient)));
        List<FoodDTO> foodDTOList = new ArrayList<>();
        for (HintDTO hintDTO : rootFoodDTO.getHintDTOS()) {
            foodDTOList.add(hintDTO.getFoodDTO());
        }

        for (int i = 0; i < 4; i++) {
            String link = rootFoodDTO.getLinksDTO().getNextDTO().getHref();
            rootFoodDTO = FoodApiService.getFoodRootDetailsObject(URLService.getApi(link));
            for (HintDTO hintDTO : rootFoodDTO.getHintDTOS())
                foodDTOList.add(hintDTO.getFoodDTO());

        }
        List<Food> foodList = foodService.saveFoodDTOList(foodDTOList);

        for (int i = 0; i < foodList.size(); i++) {
            foodDTOList.get(i).setFoodId(String.valueOf(foodList.get(i).getId()));
        }
        return foodDTOList;

    }

    @PostMapping(path = "/search/add")
    public final FoodEaten addFoodEaten(
            @RequestBody final Map<String, Object> payload
    ) {
        Long foodId = Long.valueOf(payload.get("foodId").toString());
        Double quantity = (Double) payload.get("quantity");
        Food food = foodService.searchById(foodId);
        LocalDate localDate = LocalDate.now();
        User user = userService.findUserByEmail("andreian@gmail.com");
        return foodService.saveOneFoodEaten(user, food, localDate, quantity);
    }

    @GetMapping(path = "/eaten/get/{id}")
    public Long findFoodEatenById(@PathVariable final Long id) {
        return foodService.findById(id).getUser().getId();
    }

    @GetMapping(path = "/eaten/stats/{id}")
    public final FoodEatenDigest getFoodEeatenNutrients(
            @PathVariable final Long id
    ) {
        return foodService.getNutrientsFromFoodEaten(id);
    }

    @GetMapping(path = "/eaten/stats/date")
    public final FoodEatenDigest getNutrientsByDate(
            @RequestParam final String date
    ) {
        User user = userService.findUserByEmail("andreian@gmail.com");
        return foodService.getNutrientsFromDate(user, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
