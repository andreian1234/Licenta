package com.licenta.dto.convertor;

import com.licenta.dto.NutrientsDTO;
import com.licenta.models.Nutrients;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class NutrientsDTOToNutrients implements Converter<NutrientsDTO, Nutrients> {
    @Override
    public Nutrients convert(NutrientsDTO nutrientsDTO) {

        Nutrients nutrients = null;
        if (nutrientsDTO != null) {
            nutrients = new Nutrients(
                    nutrientsDTO.getEnercKcal(),
                    nutrientsDTO.getProtein(),
                    nutrientsDTO.getFat(),
                    nutrientsDTO.getCarbs(),
                    nutrientsDTO.getFiber()
            );
        }
        return nutrients;
    }
}
