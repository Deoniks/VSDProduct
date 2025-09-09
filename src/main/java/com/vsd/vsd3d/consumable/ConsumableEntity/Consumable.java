package com.vsd.vsd3d.consumable.ConsumableEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consumables",
        uniqueConstraints = @UniqueConstraint(name = "uk_consumable_sku", columnNames = "sku"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private String sku;

    @Column(length = 1000)
    private String description;
}
