package com.licenta.dto.convertor;

import com.licenta.dto.MeasureDTO;
import com.licenta.models.Measure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class MeasureDTOTOMeasure implements Converter<MeasureDTO, Measure> {
    @Override
    public Measure convert(MeasureDTO measureDTO) {
        Measure measure = null;
        if (measureDTO != null) {
            measure = new Measure(
                    measureDTO.getLabel(),
                    measureDTO.getWeight()
            );
        }
        return measure;
    }
}
