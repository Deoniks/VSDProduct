package com.vsd.vsd3d.inventory.repository;

import com.vsd.vsd3d.inventory.entity.InventoryMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement,Long> {
    // Остаток по товару
    @Query("""
      select coalesce(sum(case when m.type = com.vsd.vsd3d.inventory.InventoryMovement.MovementType.IN
                               then m.quantity else -m.quantity end), 0)
      from InventoryMovement m
      where m.product.id = :productId
        and (:to is null or m.occurredAt <= :to)
    """)
    BigDecimal getProductStock(@Param("productId") Long productId,
                               @Param("to") LocalDateTime toInclusive);

    // Остаток по расходнику
    @Query("""
      select coalesce(sum(case when m.type = com.vsd.vsd3d.inventory.InventoryMovement.MovementType.IN
                               then m.quantity else -m.quantity end), 0)
      from InventoryMovement m
      where m.consumable.id = :consumableId
        and (:to is null or m.occurredAt <= :to)
    """)
    BigDecimal getConsumableStock(@Param("consumableId") Long consumableId,
                                  @Param("to") LocalDateTime toInclusive);

    Page<InventoryMovement> findByProduct_IdOrderByOccurredAtDesc(Long productId, Pageable pageable);
    Page<InventoryMovement> findByConsumable_IdOrderByOccurredAtDesc(Long consumableId, Pageable pageable);

    boolean existsByRefTypeAndRefId(InventoryMovement.RefType refType, Long refId);
    void deleteByRefTypeAndRefId(InventoryMovement.RefType refType, Long refId);
}
