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
public final class RecipeToRecipeDTO implements Converter<Recipe, RecipeDTO> {
    public final IngredientToIngredientDTO ingredientToIngredientDTO;
    public final DigestToDigestDTO digestToDigestDTO;


    public RecipeToRecipeDTO(IngredientToIngredientDTO ingredientToIngredientDTO, DigestToDigestDTO digestToDigestDTO) {
        this.ingredientToIngredientDTO = ingredientToIngredientDTO;
        this.digestToDigestDTO = digestToDigestDTO;
    }

    @Override
    public RecipeDTO convert(Recipe recipe) {
        RecipeDTO recipeDTO = null;
        if (recipe != null)
            recipeDTO = new RecipeDTO(
                    recipe.getId(),
                    recipe.getLabel(),
                    recipe.getImage(),
                    recipe.getUrl(),
                    recipe.getYield(),
                    recipe.getCalories(),
                    recipe.getTotalWeight(),
                    recipe.getCuisineTypeList(),
                    recipe.getMealTypeList(),
                    recipe.getDishTypeList(),
                    recipe.getDietLabelsList(),
                    recipe.getHealthLabelsList(),
                    recipe.getCautionsList(),
                    recipe.getIngredientLinesList(),
                    getIngredientList(recipe.getIngredientList()),
                    getDigestList(recipe.getDigestList()),
                    recipe.getSelf()
            );

        return recipeDTO;
    }

    public List<DigestDTO> getDigestList(List<Digest> digestList) {
        List<DigestDTO> digestDTOList = new ArrayList<>();

        for (val digest : digestList) {
            digestDTOList.add(digestToDigestDTO.convert(digest));
        }
        return digestDTOList;
    }


    public List<IngredientDTO> getIngredientList(List<Ingredient> ingredientList) {
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();

        for (val ingredient : ingredientList) {
            ingredientDTOS.add(ingredientToIngredientDTO.convert(ingredient));
        }
        return ingredientDTOS;
    }
}
