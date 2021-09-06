package com.licenta.dto.convertor;

import com.licenta.dto.FoodDTO;
import com.licenta.models.Food;
import com.licenta.models.Measure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class FoodDTOToFood implements Converter<FoodDTO, Food> {

    private final MeasureDTOTOMeasure measureDTOTOMeasure;
    private final NutrientsDTOToNutrients nutrientsDTOToNutrients;

    public FoodDTOToFood(MeasureDTOTOMeasure measureDTOTOMeasure, NutrientsDTOToNutrients nutrientsDTOToNutrients) {
        this.measureDTOTOMeasure = measureDTOTOMeasure;
        this.nutrientsDTOToNutrients = nutrientsDTOToNutrients;
    }

    @Override
    public Food convert(FoodDTO foodDTO) {
        Food food = null;
        Measure measure = null;
        if (foodDTO != null) {
            food = new Food(
                    foodDTO.getFoodId(),
                    foodDTO.getLabel(),
                    nutrientsDTOToNutrients.convert(foodDTO.getNutrientsDTO()),
                    foodDTO.getCategory(),
                    foodDTO.getCategoryLabel(),
                    foodDTO.getImage(),
                    measureDTOTOMeasure.convert(foodDTO.getMeasureDTO())
            );
        }
        return food;
    }
}
