package com.vsd.vsd3d.consumable.ConsumableDto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ConsumablePurchaseDto {
    private Long id;

    @NotNull
    private Long consumableId;

    @NotNull
    private LocalDate date;

    @NotNull
    @DecimalMin("0.000")
    @Digits(integer = 9, fraction = 3)
    private BigDecimal quantity;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal unitPrice;

    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal deliveryCost;

    private String comment;
}
