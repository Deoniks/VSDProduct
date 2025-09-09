package com.vsd.vsd3d.inventory.repository;

import com.vsd.vsd3d.inventory.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface InventoryMovementRepository extends JpaRepository<InventoryMovement,Long> {
    @Query("""
            select coalesce(sum(
            case when m.type = com.vsd.vsd3d.inventory.InventoryMovement.MovementType.IN
            then m.quantity else -m.quantity end), 0)
    from InventoryMovement m
    where m.product.id = :productId
    """)
    BigDecimal getProductStock(@Param("productId") Long productId);


    @Query("""
      select coalesce(sum(
        case when m.type = com.vsd.vsd3d.inventory.InventoryMovement.MovementType.IN
             then m.quantity else -m.quantity end), 0)
      from InventoryMovement m
      where m.consumable.id = :consumableId
    """)
    BigDecimal getConsumableStock(@Param("consumableId") Long consumableId);
}
