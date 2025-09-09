package com.vsd.vsd3d.consumable.ConsumableDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConsumableDto {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String unit;

    @NotBlank
    private String sku;

    private String description;
}
