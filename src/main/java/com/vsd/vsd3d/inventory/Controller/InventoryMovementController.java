package com.vsd.vsd3d.inventory.Controller;

import com.vsd.vsd3d.inventory.entity.InventoryMovement;
import com.vsd.vsd3d.inventory.repository.InventoryMovementRepository;
import com.vsd.vsd3d.inventory.service.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryMovementController {
    private final InventoryMovementService service;
    private final InventoryMovementRepository repo;

    @GetMapping("/stock/product/{productId}")
    public BigDecimal productStock(@PathVariable Long productId) {
        return service.getProductStock(productId);
    }

    @GetMapping("/stock/consumable/{consumableId}")
    public BigDecimal consumableStock(@PathVariable Long consumableId) {
        return service.getConsumableStock(consumableId);
    }

    @GetMapping("/history/product/{productId}")
    public Page<InventoryMovement> productHistory(@PathVariable Long productId,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "20") int size) {
        return repo.findByProduct_IdOrderByOccurredAtDesc(productId, PageRequest.of(page, size));
    }

    @GetMapping("/history/consumable/{consumableId}")
    public Page<InventoryMovement> consumableHistory(@PathVariable Long consumableId,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "20") int size) {
        return repo.findByConsumable_IdOrderByOccurredAtDesc(consumableId, PageRequest.of(page, size));
    }
}
