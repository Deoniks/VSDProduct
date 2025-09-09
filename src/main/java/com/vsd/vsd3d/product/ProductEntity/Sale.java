package com.vsd.vsd3d.product.ProductEntity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;


    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salePrice;

    @Column(precision = 5, scale = 2)
    private BigDecimal taxPercent;

    @Column(precision = 5, scale = 2)
    private BigDecimal commissionPercent;

    private String source;   // KASPI / MANUAL / etc
    private String comment;
}
