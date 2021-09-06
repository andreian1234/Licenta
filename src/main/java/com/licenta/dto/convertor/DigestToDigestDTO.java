package com.licenta.dto.convertor;

import com.licenta.dto.DigestDTO;
import com.licenta.models.Digest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DigestToDigestDTO implements Converter<Digest, DigestDTO> {

    @Override
    public DigestDTO convert(Digest digest) {
        DigestDTO digestDTO = null;
        if (digest != null)
            digestDTO = new DigestDTO(
                    digest.getLabel(),
                    digest.getTag(),
                    digest.getSchemaOrgTag(),
                    digest.getTotal(),
                    digest.isHasRDI(),
                    digest.getDaily(),
                    digest.getUnit()
            );
        return digestDTO;
    }
}
