package com.licenta.controllers;

import com.licenta.dto.FoodDTO;
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
        return this.foodRepository.findAll();
    }

    @SneakyThrows
    @GetMapping("/search/{ingredient}")
    public final List<FoodDTO> addFoodToDB(@PathVariable final String ingredient) {


//        userService.createUser(new UserDTO(
//                User.Gender.MALE,
//                LocalDate.of(1997,12,2),
//                180,
//                User.ActivityLevel.EXTREMELY_ACTIVE,
//                "andrei@gmail.com",
//                "Andrei",
//                "Ancuta",
//                100,
//                30,
//                2500
//        ), "parola");

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
        System.out.println("FoodId: " + foodId);
        Double quantity = (Double) payload.get("quantity");
        System.out.println("Quantity: " + quantity);
        Food food = foodService.searchById(foodId);
        System.out.println(food);
        LocalDate localDate = LocalDate.now();
        User user = userService.findUserByEmail("andrei@gmail.com");
        return foodService.saveOneFoodEaten(user, food, localDate, quantity);

    }

}
