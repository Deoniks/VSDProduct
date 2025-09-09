package com.vsd.vsd3d.inventory.entity;

import com.vsd.vsd3d.consumable.ConsumableEntity.Consumable;
import com.vsd.vsd3d.product.ProductEntity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movements",
        indexes = {
                @Index(name = "idx_mov_product_date", columnList = "product_id,occurredAt"),
                @Index(name = "idx_mov_consumable_date", columnList = "consumable_id,occurredAt"),
                @Index(name = "idx_mov_ref", columnList = "refType,refId")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryMovement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private LocalDateTime occurredAt;

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private MovementType type;

    @Enumerated(EnumType.STRING) @Column(nullable = false, name = "ref_type")
    private RefType refType;

    @Column(nullable = false,name = "ref_id")
    private Long refId;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal quantity;

    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;          // РОВНО одно из двух
    @ManyToOne @JoinColumn(name = "consumable_id")
    private Consumable consumable;    // РОВНО одно из двух

}

/*@Entity
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
    private Consumable consumable;  // ровно одно из двух должно быть*/

