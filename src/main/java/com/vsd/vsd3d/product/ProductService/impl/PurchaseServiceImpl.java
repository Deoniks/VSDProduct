package com.vsd.vsd3d.product.ProductService.impl;

import com.vsd.vsd3d.exception.NotFoundException;
import com.vsd.vsd3d.inventory.service.InventoryMovementService;
import com.vsd.vsd3d.inventory.entity.InventoryMovement.RefType;
import com.vsd.vsd3d.product.ProductDto.PurchaseDto;
import com.vsd.vsd3d.product.ProductEntity.Product;
import com.vsd.vsd3d.product.ProductEntity.Purchase;
import com.vsd.vsd3d.product.ProductMapper.PurchaseMapper;
import com.vsd.vsd3d.product.ProductRepository.ProductRepository;
import com.vsd.vsd3d.product.ProductRepository.PurchaseRepository;
import com.vsd.vsd3d.product.ProductService.PurchaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final PurchaseMapper purchaseMapper;
    private final InventoryMovementService inventoryService;

    @Override
    public Page<PurchaseDto> getAll(Pageable pageable) {
        return purchaseRepository.findAll(pageable).map(purchaseMapper::toDto);
    }

    @Override
    public PurchaseDto getById(Long id) {
        return purchaseRepository.findById(id).map(purchaseMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    @Transactional
    public PurchaseDto create(PurchaseDto dto) {
        Product product = productRepository.findById(dto.getProduct_id())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        var e = purchaseMapper.toEntity(dto);
        e.setProduct(product);
        var saved = purchaseRepository.save(e);

        inventoryService.addProductIn(
                dto.getProduct_id(), saved.getId(),
                RefType.PURCHASE, saved.getQuantity(), saved.getDate()
        );
        return purchaseMapper.toDto(saved);
    }

    @Override
    public PurchaseDto update(Long id, PurchaseDto dto) {
        var e = purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Purchase not found"));
        if (!e.getProduct().getId().equals(dto.getProduct_id())) {
            var p = productRepository.findById(dto.getProduct_id())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            e.setProduct(p);
        }
        e.setDate(dto.getDate());
        e.setQuantity(dto.getQuantity());
        e.setUnitPrice(dto.getUnitPrice());
        e.setDeliveryCost(dto.getDeliveryCost());
        e.setTaxPercent(dto.getTaxPercent());
        e.setCommissionPercent(dto.getCommissionPercent());
        e.setComment(dto.getComment());

        inventoryService.deleteByRef(RefType.PURCHASE, e.getId());
        var saved = purchaseRepository.save(e);
        inventoryService.addProductIn(
                saved.getProduct().getId(), saved.getId(),
                RefType.PURCHASE, saved.getQuantity(), saved.getDate()
        );
        return purchaseMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if(!purchaseRepository.existsById(id)) throw new NotFoundException("Purchase not found");
        inventoryService.deleteByRef(RefType.PURCHASE, id);
        purchaseRepository.deleteById(id);
    }
}
