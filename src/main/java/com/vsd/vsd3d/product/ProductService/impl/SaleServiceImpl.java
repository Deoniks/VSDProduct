package com.vsd.vsd3d.product.ProductService.impl;

import com.vsd.vsd3d.exception.NotFoundException;
import com.vsd.vsd3d.inventory.entity.RefType;
import com.vsd.vsd3d.inventory.service.InventoryMovementService;
import com.vsd.vsd3d.product.ProductDto.SaleDto;
import com.vsd.vsd3d.product.ProductEntity.Product;
import com.vsd.vsd3d.product.ProductEntity.Sale;
import com.vsd.vsd3d.product.ProductMapper.SaleMapper;
import com.vsd.vsd3d.product.ProductRepository.ProductRepository;
import com.vsd.vsd3d.product.ProductRepository.SaleRepository;
import com.vsd.vsd3d.product.ProductService.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepo;
    private final ProductRepository productRepo;
    private final SaleMapper mapper;
    private final InventoryMovementService inventoryService;

    @Override
    public Page<SaleDto> getAll(Pageable pageable) {
        return saleRepo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public SaleDto getById(Long id) {
        return saleRepo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Sale not found"));
    }

    @Override
    @Transactional
    public SaleDto create(SaleDto dto) {
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found"));

        inventoryService.assertEnoughProduct(dto.getProductId(), dto.getQuantity());

        var e = mapper.toEntity(dto);
        e.setProduct(product);
        var saved = saleRepo.save(e);

        inventoryService.addProductOut(
                dto.getProductId(), saved.getId(),
                RefType.PRODUCT_SALE, saved.getQuantity(), saved.getDate()
        );
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public SaleDto update(Long id, SaleDto dto) {
        var existing = saleRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Sale not found"));

        if (!existing.getProduct().getId().equals(dto.getProductId())) {
            var p = productRepo.findById(dto.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            existing.setProduct(p);
        }

        inventoryService.assertEnoughProduct(dto.getProductId(), dto.getQuantity());

        existing.setDate(dto.getDate());
        existing.setQuantity(dto.getQuantity());
        existing.setSalePrice(dto.getSalePrice());
        existing.setTaxPercent(dto.getTaxPercent());
        existing.setCommissionPercent(dto.getCommissionPercent());
        existing.setSource(dto.getSource());
        existing.setComment(dto.getComment());

        inventoryService.deleteByRef(RefType.PRODUCT_SALE, existing.getId());
        var saved = saleRepo.save(existing);
        inventoryService.addProductOut(
                saved.getProduct().getId(), saved.getId(),
                RefType.PRODUCT_SALE, saved.getQuantity(), saved.getDate()
        );
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!saleRepo.existsById(id)) throw new NotFoundException("Sale not found");
        inventoryService.deleteByRef(RefType.PRODUCT_SALE, id);
        saleRepo.deleteById(id);
    }
}
