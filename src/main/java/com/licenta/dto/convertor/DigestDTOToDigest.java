package com.licenta.dto.convertor;

import com.licenta.dto.DigestDTO;
import com.licenta.models.Digest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class DigestDTOToDigest implements Converter<DigestDTO, Digest> {
    @Override
    public Digest convert(DigestDTO digestDTO) {
        Digest digest = null;
        if (digestDTO != null)
            digest = new Digest(
                    digestDTO.getLabel(),
                    digestDTO.getTag(),
                    digestDTO.getSchemaOrgTag(),
                    digestDTO.getTotal(),
                    digestDTO.isHasRDI(),
                    digestDTO.getDaily(),
                    digestDTO.getUnit()
            );
        return digest;
    }
}
