package com.licenta.services;

import com.licenta.dto.RecipeDTO;
import com.licenta.dto.RecipeEatenDigest;
import com.licenta.dto.convertor.RecipeDTOToRecipe;
import com.licenta.models.Digest;
import com.licenta.models.Recipe;
import com.licenta.models.RecipeEaten;
import com.licenta.models.User;
import com.licenta.repositories.RecipeEatenRepository;
import com.licenta.repositories.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeEatenRepository recipeEatenRepository;
    private final RecipeDTOToRecipe recipeDTOToRecipe;


    public List<Recipe> saveRecipeDTOList(List<RecipeDTO> recipeDTOList) {
        List<Recipe> recipeList = new ArrayList<>();
        for (val recipeDto : recipeDTOList) {
            recipeList.add(recipeDTOToRecipe.convert(recipeDto));
        }
        recipeRepository.saveAll(recipeList);
        return recipeList;
    }

    public List<Recipe> searchRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> searchLastRecipesAdded() {
        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeList.subList(recipeList.size() - 20, recipeList.size());
    }

    public RecipeEaten saveRecipeEaten(User user, Recipe recipe, LocalDate localDate, double quantity) {
        RecipeEaten recipeEaten = new RecipeEaten();

        recipeEaten.setRecipe(recipe);
        recipeEaten.setDate(localDate);
        recipeEaten.setQuantity(quantity);
        recipeEaten.setUser(user);
        recipeEatenRepository.save(recipeEaten);
        return recipeEaten;
    }

    public Recipe saveOneRecipe(RecipeDTO recipeDTO) {
        return recipeRepository.save(recipeDTOToRecipe.convert(recipeDTO));
    }

    public RecipeEaten findById(Long id) {
        return recipeEatenRepository.findById(id).get();
    }

    public RecipeEatenDigest getRecipeEatenDigest(Long id) {
        RecipeEaten recipeEaten = recipeEatenRepository.getById(id);
        List<Digest> digestList = recipeEaten.getRecipe().getDigestList();
        for (Digest digest : digestList) {
            digest.setDaily(digest.getDaily() * recipeEaten.getRatio());
            digest.setTotal(digest.getTotal() * recipeEaten.getRatio());
        }
        return new RecipeEatenDigest(id, digestList, recipeEaten.getRecipe().getCalories() * recipeEaten.getRatio(), recipeEaten.getRecipe().getTotalWeight() * recipeEaten.getRatio());
    }

    public RecipeEatenDigest getDigestFromDate(User user, LocalDate localDate) {
        val recipes = recipeEatenRepository.findByUserEqualsAndDateEquals(user, localDate);
        double calories, totalWeight;
        RecipeEatenDigest recipeEaten;
        List<Digest> recipesDigest = new ArrayList<>();
        if (recipes.size() != 0) {
            recipeEaten = getRecipeEatenDigest(recipes.get(0).getId());
            recipesDigest = recipeEaten.getDigestList();
            calories = recipeEaten.getCalories();
            totalWeight = recipeEaten.getTotalWeight();
            if (recipes.size() > 1) {
                for (int i = 1; i < recipes.size(); i++) {
                    val recipeEatenDigest = getRecipeEatenDigest(recipes.get(i).getId());
                    for (int j = 0; j < recipeEatenDigest.getDigestList().size(); j++) {
                        recipesDigest.get(j).setTotal(recipesDigest.get(j).getTotal() + recipeEatenDigest.getDigestList().get(j).getTotal());
                        recipesDigest.get(j).setDaily(recipesDigest.get(j).getDaily() + recipeEatenDigest.getDigestList().get(j).getDaily());
                    }
                    calories += recipeEatenDigest.getCalories();
                    totalWeight += recipeEatenDigest.getTotalWeight();
                }
            }
        } else {
            calories = 0;
            totalWeight = 0;
        }

        return new RecipeEatenDigest(Long.valueOf(1), recipesDigest, calories, totalWeight);
    }
}
