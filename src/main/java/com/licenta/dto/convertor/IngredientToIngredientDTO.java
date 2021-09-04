package com.licenta.dto.convertor;

import com.licenta.dto.IngredientDTO;
import com.licenta.models.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class IngredientToIngredientDTO implements Converter<Ingredient, IngredientDTO> {

    @Override
    public IngredientDTO convert(Ingredient ingredient) {
        IngredientDTO ingredientDTO = null;
        if (ingredient != null)
            ingredientDTO = new IngredientDTO(
                    ingredient.getText(),
                    ingredient.getWeight(),
                    ingredient.getFoodCategory(),
                    ingredient.getFoodId(),
                    ingredient.getImage()
            );
        return ingredientDTO;
    }
}
