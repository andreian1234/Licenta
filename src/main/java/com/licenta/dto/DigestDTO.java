package com.licenta.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DigestDTO implements Serializable {
     String label;
     String tag;
     String schemaOrgTag;
     double total;
     boolean hasRDI;
     double daily;
     String unit;

     public DigestDTO(String label, String tag, String schemaOrgTag, double total, boolean hasRDI, double daily, String unit) {
          this.label = label;
          this.tag = tag;
          this.schemaOrgTag = schemaOrgTag;
          this.total = total;
          this.hasRDI = hasRDI;
          this.daily = daily;
          this.unit = unit;
     }

     List<SubDTO> subDTO;


     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (!(o instanceof DigestDTO)) return false;

          DigestDTO digestDTO = (DigestDTO) o;

          if (Double.compare(digestDTO.total, total) != 0) return false;
          if (hasRDI != digestDTO.hasRDI) return false;
          if (Double.compare(digestDTO.daily, daily) != 0) return false;
          if (label != null ? !label.equals(digestDTO.label) : digestDTO.label != null) return false;
          if (tag != null ? !tag.equals(digestDTO.tag) : digestDTO.tag != null) return false;
          if (schemaOrgTag != null ? !schemaOrgTag.equals(digestDTO.schemaOrgTag) : digestDTO.schemaOrgTag != null)
               return false;
          if (unit != null ? !unit.equals(digestDTO.unit) : digestDTO.unit != null) return false;
          return subDTO != null ? subDTO.equals(digestDTO.subDTO) : digestDTO.subDTO == null;
     }

     @Override
     public int hashCode() {
          int result;
          long temp;
          result = label != null ? label.hashCode() : 0;
          result = 31 * result + (tag != null ? tag.hashCode() : 0);
          result = 31 * result + (schemaOrgTag != null ? schemaOrgTag.hashCode() : 0);
          temp = Double.doubleToLongBits(total);
          result = 31 * result + (int) (temp ^ (temp >>> 32));
          result = 31 * result + (hasRDI ? 1 : 0);
          temp = Double.doubleToLongBits(daily);
          result = 31 * result + (int) (temp ^ (temp >>> 32));
          result = 31 * result + (unit != null ? unit.hashCode() : 0);
          result = 31 * result + (subDTO != null ? subDTO.hashCode() : 0);
          return result;
     }
}