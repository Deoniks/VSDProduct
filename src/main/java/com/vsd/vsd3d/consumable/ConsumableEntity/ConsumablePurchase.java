package com.vsd.vsd3d.consumable.ConsumableEntity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "consumable_purchases")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsumablePurchase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "consumable_id")
    private Consumable consumable;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal unitPrice;

    @Column(precision = 12, scale = 2)
    private BigDecimal deliveryCost;

    @Column(length = 1000)
    private String comment;
}
