import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface InventoryMovementService {
    
    void assertEnoughProduct(Long productId, BigDecimal qty);
    void assertEnoughConsumable(Long consumableId, BigDecimal qty);

    // Movements
    void addProductIn(Long productId, Long refId, RefType refType,
                      BigDecimal qty, LocalDate date);

    void addProductOut(Long productId, Long refId, RefType refType,
                       BigDecimal qty, LocalDate date);

    void addConsumableIn(Long consumableId, Long refId, RefType refType,
                         BigDecimal qty, LocalDate date);

    void addConsumableOut(Long consumableId, Long refId, RefType refType,
                          BigDecimal qty, LocalDate date);

    void deleteByRef(RefType refType, Long refId);

    BigDecimal getProductStock(Long productId);
    BigDecimal getConsumableStock(Long consumableId);

    BigDecimal getProductStock(Long productId, LocalDateTime toInclusive);
    BigDecimal getConsumableStock(Long consumableId, LocalDateTime toInclusive);

    Page<InventoryMovement> findByProduct(Long productId, Pageable pageable);
    Page<InventoryMovement> findByConsumable(Long consumableId, Pageable pageable);

    // ----- Защита от отрицательных остатков (опционально) -----
    //void assertEnoughProduct(Long productId, BigDecimal qtyNeeded);
    //void assertEnoughConsumable(Long consumableId, BigDecimal qtyNeeded);
}
