package com.arek.praktyki.JsonDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Holding {
    private String symbol;
    private String description;
    private String weight;
}
