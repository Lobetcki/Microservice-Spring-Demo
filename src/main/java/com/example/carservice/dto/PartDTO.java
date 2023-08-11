package com.example.carservice.dto;

import com.example.carservice.model.Parts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartDTO {
    private String serialNumber;
    private String namePart;

    public static PartDTO fromPartDTO(Parts part) {
        PartDTO partDTO = new PartDTO();
        partDTO.setSerialNumber(part.getSerialNumber());
        partDTO.setNamePart(part.getNamePart());
        return partDTO;
    }

    public Parts toPart() {
        Parts parts = new Parts();
        parts.setSerialNumber(this.serialNumber);
        parts.setNamePart(this.namePart);
        return parts;
    }
}
