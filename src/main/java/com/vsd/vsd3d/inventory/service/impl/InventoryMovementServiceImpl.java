package com.vsd.vsd3d.inventory.service.impl;

import com.vsd.vsd3d.consumable.ConsumableEntity.Consumable;
import com.vsd.vsd3d.consumable.ConsumableRepository.ConsumableRepository;
import com.vsd.vsd3d.exception.NotFoundException;
import com.vsd.vsd3d.exception.ValidationException;
import com.vsd.vsd3d.inventory.entity.InventoryMovement;
import com.vsd.vsd3d.inventory.entity.MovementType;
import com.vsd.vsd3d.inventory.entity.RefType;
import com.vsd.vsd3d.inventory.repository.InventoryMovementRepository;
import com.vsd.vsd3d.inventory.service.InventoryMovementService;
import com.vsd.vsd3d.product.ProductEntity.Product;
import com.vsd.vsd3d.product.ProductRepository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InventoryMovementServiceImpl implements InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;
    private final ProductRepository productRepository;
    private final ConsumableRepository consumableRepository;


    @Override
    public void assertEnoughProduct(Long productId, BigDecimal qty) {
        if (getProductStock(productId).compareTo(qty) < 0) {
            throw new ValidationException("Insufficient product stock: id=" + productId);
        }
    }

    @Override
    public void assertEnoughConsumable(Long consumableId, BigDecimal qty) {
        if (getConsumableStock(consumableId).compareTo(qty) < 0) {
            throw new ValidationException("Insufficient consumable stock: id=" + consumableId);
        }
    }

    // ---------- Movements ----------
    @Override
    public void addProductIn(Long productId, Long refId, RefType refType,
                             BigDecimal qty, LocalDate date) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        save(MovementType.IN, refType, refId, qty, date, product, null);
    }

    @Override
    public void addProductOut(Long productId, Long refId, RefType refType,
                              BigDecimal qty, LocalDate date) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        save(MovementType.OUT, refType, refId, qty, date, product, null);
    }

    @Override
    public Page<InventoryMovement> findByProduct(Long productId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<InventoryMovement> findByConsumable(Long consumableId, Pageable pageable) {
        return null;
    }

    @Override
    public void addConsumableIn(Long consumableId, Long refId, RefType refType,
                                BigDecimal qty, LocalDate date) {
        Consumable c = consumableRepository.findById(consumableId)
                .orElseThrow(() -> new NotFoundException("Consumable not found"));
        save(MovementType.IN, refType, refId, qty, date, null, c);
    }

    @Override
    public void addConsumableOut(Long consumableId, Long refId, RefType refType,
                                 BigDecimal qty, LocalDate date) {
        Consumable c = consumableRepository.findById(consumableId)
                .orElseThrow(() -> new NotFoundException("Consumable not found"));
        save(MovementType.OUT, refType, refId, qty, date, null, c);
    }

    @Override
    public void deleteByRef(RefType refType, Long refId) {
        inventoryMovementRepository.deleteByRefTypeAndRefId(refType, refId);
    }

    @Override
    public BigDecimal getProductStock(Long productId, LocalDateTime toInclusive) {
        return  inventoryMovementRepository.getProductStock(
                productId, toInclusive, MovementType.IN, MovementType.OUT
        );
    }

    @Override
    public BigDecimal getConsumableStock(Long consumableId, LocalDateTime toInclusive) {
        return inventoryMovementRepository.getConsumableStock(
                consumableId, toInclusive, MovementType.IN, MovementType.OUT
        );
    }

    // ---------- Internal ----------
    private void save(MovementType type,
                      RefType refType,
                      Long refId,
                      BigDecimal qty,
                      LocalDate docDate,
                      Product product,
                      Consumable consumable) {

        var mv = InventoryMovement.builder()
                .type(type)
                .refType(refType)
                .refId(refId)
                .quantity(qty)
                .occurredAt(docDate != null ? docDate.atStartOfDay() : LocalDateTime.now())
                .product(product)
                .consumable(consumable)
                .build();
        inventoryMovementRepository.save(mv);
    }
}
