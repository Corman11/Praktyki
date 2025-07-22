package com.arek.praktyki.JsonDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SectorAllocation {
    private String sector;
    private String weight;
}
