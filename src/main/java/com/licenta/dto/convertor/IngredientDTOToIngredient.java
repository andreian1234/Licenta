package com.licenta.dto.convertor;

import com.licenta.dto.IngredientDTO;
import com.licenta.models.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class IngredientDTOToIngredient implements Converter<IngredientDTO, Ingredient> {
    @Override
    public Ingredient convert(IngredientDTO ingredientDTO) {
        Ingredient ingredient = null;
        if (ingredientDTO != null)
            ingredient = new Ingredient(
                    ingredientDTO.getText(),
                    ingredientDTO.getWeight(),
                    ingredientDTO.getFoodCategory(),
                    ingredientDTO.getFoodId(),
                    ingredientDTO.getImage()
            );
        return ingredient;
    }
}
