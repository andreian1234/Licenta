package com.licenta.services;

import com.licenta.dto.RecipeEatenDigest;
import com.licenta.dto.convertor.FoodDTOToFood;
import com.licenta.dto.convertor.RecipeDTOToRecipe;
import com.licenta.models.User;
import com.licenta.repositories.FoodEatenRepository;
import com.licenta.repositories.FoodRepository;
import com.licenta.repositories.RecipeEatenRepository;
import com.licenta.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class HistoryService {

    private final RecipeRepository recipeRepository;
    private final RecipeEatenRepository recipeEatenRepository;
    private final RecipeDTOToRecipe recipeDTOToRecipe;
    private final FoodRepository foodRepository;
    private final FoodDTOToFood foodDTOToFood;
    private final FoodEatenRepository foodEatenRepository;
    private final FoodService foodService;
    private final RecipeService recipeService;

    public RecipeEatenDigest getTotalDigestFromDate(User user, LocalDate localDate) {
        val nutrients = foodService.getNutrientsFromDate(user, localDate);
        val digest = recipeService.getDigestFromDate(user, localDate);

        digest.getDigestList().get(0).setDaily((digest.getDigestList().get(0).getTotal() + nutrients.getNutrients().getFat()) /
                digest.getDigestList().get(0).getTotal() * digest.getDigestList().get(0).getDaily());

        digest.getDigestList().get(0).setTotal(
                digest.getDigestList().get(0).getTotal() + nutrients.getNutrients().getFat()
        );


        digest.getDigestList().get(1).setDaily((digest.getDigestList().get(1).getTotal() + nutrients.getNutrients().getCarbs()) /
                digest.getDigestList().get(1).getTotal() * digest.getDigestList().get(1).getDaily());

        digest.getDigestList().get(1).setTotal(
                digest.getDigestList().get(1).getTotal() + nutrients.getNutrients().getCarbs()
        );

        digest.getDigestList().get(2).setDaily((digest.getDigestList().get(2).getTotal() + nutrients.getNutrients().getProtein()) /
                digest.getDigestList().get(2).getTotal() * digest.getDigestList().get(2).getDaily());

        digest.getDigestList().get(2).setTotal(
                digest.getDigestList().get(2).getTotal() + nutrients.getNutrients().getProtein()
        );

        digest.setCalories(digest.getCalories() + nutrients.getNutrients().getEnercKcal());
        digest.setTotalWeight(digest.getTotalWeight() + nutrients.getTotalWeight());

        return digest;
    }

    public RecipeEatenDigest getTotalDigestFromDateToDate(User userByEmail, LocalDate parse, LocalDate parse1) {
        RecipeEatenDigest recipeEatenDigestPeriod = new RecipeEatenDigest();
        List<RecipeEatenDigest> recipeEatenDigestList = new ArrayList<>();
        double fatDaily = 0;
        double fatTotal = 0;
        for (int i = 0; i <= Period.between(parse, parse1).getDays(); i++) {
            recipeEatenDigestList.add(getTotalDigestFromDate(userByEmail, parse));
            parse = parse.plusDays(1);
        }
        for (RecipeEatenDigest recipeEatenDigest : recipeEatenDigestList) {

        }
        return recipeEatenDigestList.get(0);
    }
}
