package com.licenta.dto.convertor;

import com.licenta.dto.DigestDTO;
import com.licenta.dto.IngredientDTO;
import com.licenta.dto.RecipeDTO;
import com.licenta.models.Digest;
import com.licenta.models.Ingredient;
import com.licenta.models.Recipe;
import lombok.val;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class RecipeDTOToRecipe implements Converter<RecipeDTO, Recipe> {

    public final IngredientDTOToIngredient ingredientDTOToIngredient;
    public final DigestDTOToDigest digestDTOToDigest;

    public RecipeDTOToRecipe(IngredientDTOToIngredient ingredientDTOToIngredient, DigestDTOToDigest digestDTOToDigest) {
        this.ingredientDTOToIngredient = ingredientDTOToIngredient;
        this.digestDTOToDigest = digestDTOToDigest;
    }

    @Override
    public Recipe convert(RecipeDTO recipeDTO) {
        Recipe recipe = null;
        if (recipeDTO != null)
            recipe = new Recipe(
                    recipeDTO.getLabel(),
                    recipeDTO.getImage(),
                    recipeDTO.getUrl(),
                    recipeDTO.getYield(),
                    recipeDTO.getCalories(),
                    recipeDTO.getTotalWeight(),
                    recipeDTO.getLink(),
                    recipeDTO.getDietLabels(),
                    recipeDTO.getHealthLabels(),
                    recipeDTO.getCautions(),
                    recipeDTO.getIngredientLines(),
                    recipeDTO.getCuisineType(),
                    recipeDTO.getMealType(),
                    recipeDTO.getDishType(),
                    getIngredientList(recipeDTO.getIngredientDTOS()),
                    getDigestList(recipeDTO.getDigestDTO())

            );

        return recipe;
    }

    public List<Digest> getDigestList(List<DigestDTO> digestDTOList) {
        List<Digest> digestList = new ArrayList<>();

        for (val digestDTO : digestDTOList) {
            digestList.add(digestDTOToDigest.convert(digestDTO));
        }
        return digestList;
    }


    public List<Ingredient> getIngredientList(List<IngredientDTO> ingredientDTOList) {
        List<Ingredient> ingredientList = new ArrayList<>();

        for (val ingredientDTO : ingredientDTOList) {
            ingredientList.add(ingredientDTOToIngredient.convert(ingredientDTO));
        }
        return ingredientList;
    }
}
