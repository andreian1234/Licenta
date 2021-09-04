package com.licenta.services;

import com.licenta.dto.RecipeDTO;
import com.licenta.dto.convertor.RecipeDTOToRecipe;
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
}
