package com.licenta.dto.convertor;

import com.licenta.dto.WeightDTO;
import com.licenta.models.Weight;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class WeightToWeightDTO implements Converter<Weight, WeightDTO> {

    @Override

    public WeightDTO convert(final Weight weight) {
        WeightDTO dto = null;
        if (weight != null) {
            dto = new WeightDTO(
                    weight.getId(),
                    weight.getUser().getId(),
                    weight.getDate(),
                    weight.getPounds()
            );
        }
        return dto;
    }

}
