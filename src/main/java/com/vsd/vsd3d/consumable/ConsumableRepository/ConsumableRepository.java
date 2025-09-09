package com.vsd.vsd3d.consumable.ConsumableRepository;

import com.vsd.vsd3d.consumable.ConsumableEntity.Consumable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
    boolean existsBySku(String sku);
}
