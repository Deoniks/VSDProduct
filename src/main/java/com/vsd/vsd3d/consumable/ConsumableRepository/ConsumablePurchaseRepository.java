package com.vsd.vsd3d.consumable.ConsumableRepository;

import com.vsd.vsd3d.consumable.ConsumableEntity.ConsumablePurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumablePurchaseRepository extends JpaRepository<ConsumablePurchase, Long> {
}
