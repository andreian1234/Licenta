package com.licenta.services;

import com.licenta.dto.RecipeDTO;
import com.licenta.dto.convertor.RecipeDTOToRecipe;
import com.licenta.models.Recipe;
import com.licenta.repositories.RecipeRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDTOToRecipe recipeDTOToRecipe;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, RecipeDTOToRecipe recipeDTOToRecipe) {

        this.recipeRepository = recipeRepository;
        this.recipeDTOToRecipe = recipeDTOToRecipe;
    }

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

}
