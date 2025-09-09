package com.vsd.vsd3d.consumable.ConsumableEntity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "consumable_usage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumableUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "consumable_id")
    private Consumable consumable;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal quantity;

    @Column(length = 255)
    private String reason;

    @Column(length = 1000)
    private String comment;
}
