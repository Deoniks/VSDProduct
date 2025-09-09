package com.vsd.vsd3d.inventory.entity;

import com.vsd.vsd3d.consumable.ConsumableEntity.Consumable;
import com.vsd.vsd3d.product.ProductEntity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "inventory_movements",
        indexes = {
        @Index(columnList = "product_id, date"),
        @Index(columnList = "consumable_id, date")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryMovement {

    public enum MovementType {IN, OUT}
    public enum RefType{
        PURCHASE, SALE,
        CONSUMABLE_PURCHASE, CONSUMABLE_USAGE,
        PRODUCTION_IN, PRODUCTION_OUT, ADJUSTMEN
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefType refType;

    @Column(nullable = false)
    private Long refId;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;        // ровно одно из двух должно быть

    @ManyToOne @JoinColumn(name = "consumable_id")
    private Consumable consumable;  // ровно одно из двух должно быть
}
