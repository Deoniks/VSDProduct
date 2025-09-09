package com.vsd.vsd3d.inventory.repository;

import com.vsd.vsd3d.inventory.entity.InventoryMovement;
import com.vsd.vsd3d.inventory.entity.MovementType;
import com.vsd.vsd3d.inventory.entity.RefType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement,Long> {
    // остаток по товару
    @Query("""
        select coalesce(
           sum(case 
                 when m.type = :inType  then m.quantity
                 when m.type = :outType then -m.quantity
                 else 0 
               end), 0
        )
        from InventoryMovement m
        where m.product.id = :productId
          and (:to is null or m.occurredAt <= :to)
    """)
    BigDecimal getProductStock(@Param("productId") Long productId,
                               @Param("to") LocalDateTime to,
                               @Param("inType") MovementType inType,
                               @Param("outType") MovementType outType);

    // остаток по расходнику
    @Query("""
        select coalesce(
           sum(case 
                 when m.type = :inType  then m.quantity
                 when m.type = :outType then -m.quantity
                 else 0 
               end), 0
        )
        from InventoryMovement m
        where m.consumable.id = :consumableId
          and (:to is null or m.occurredAt <= :to)
    """)
    BigDecimal getConsumableStock(@Param("consumableId") Long consumableId,
                                  @Param("to") LocalDateTime to,
                                  @Param("inType") MovementType inType,
                                  @Param("outType") MovementType outType);

    // Удаление движений по связке (для апдейта/удаления первичного документа)
    @Modifying
    @Query("delete from InventoryMovement m where m.refType = :refType and m.refId = :refId")
    void deleteByRefTypeAndRefId(@Param("refType") RefType refType, @Param("refId") Long refId);

    Page<InventoryMovement> findByProduct_IdOrderByOccurredAtDesc(Long productId, Pageable pageable);
    Page<InventoryMovement> findByConsumable_IdOrderByOccurredAtDesc(Long consumableId, Pageable pageable);
}
