package com.vsd.vsd3d.inventory.service;

import com.vsd.vsd3d.inventory.entity.InventoryMovement;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface InventoryMovementService {

    // Stocks
    BigDecimal getProductStock(Long productId);
    BigDecimal getConsumableStock(Long consumableId);
    void assertEnoughProduct(Long productId, BigDecimal qty);
    void assertEnoughConsumable(Long consumableId, BigDecimal qty);

    // Movements
    void addProductIn(Long productId, Long refId, InventoryMovement.RefType refType,
                      BigDecimal qty, LocalDate date);

    void addProductOut(Long productId, Long refId, InventoryMovement.RefType refType,
                       BigDecimal qty, LocalDate date);

    void addConsumableIn(Long consumableId, Long refId, InventoryMovement.RefType refType,
                         BigDecimal qty, LocalDate date);

    void addConsumableOut(Long consumableId, Long refId, InventoryMovement.RefType refType,
                          BigDecimal qty, LocalDate date);

    void deleteByRef(InventoryMovement.RefType refType, Long refId);
}
