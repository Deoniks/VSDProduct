package com.vsd.vsd3d.product.ProductService.impl;

import com.vsd.vsd3d.exception.NotFoundException;
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
        Purchase entity = purchaseMapper.toEntity(dto);
        entity.setProduct(product);
        return purchaseMapper.toDto(purchaseRepository.save(entity));
    }

    @Override
    public PurchaseDto update(Long id, PurchaseDto dto) {
        Purchase existing = purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Purchase not found"));
        if (!existing.getProduct().getId().equals(dto.getProduct_id())) {
            Product product = productRepository.findById(dto.getProduct_id())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            existing.setProduct(product);
        }
        existing.setDate(dto.getDate());
        existing.setQuantity(dto.getQuantity());
        existing.setUnitPrice(dto.getUnitPrice());
        existing.setDeliveryCost(dto.getDeliveryCost());
        existing.setTaxPercent(dto.getTaxPercent());
        existing.setCommissionPercent(dto.getCommissionPercent());
        existing.setComment(dto.getComment());
        return purchaseMapper.toDto(purchaseRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if(!purchaseRepository.existsById(id)) throw new NotFoundException("Purchase not found");
        purchaseRepository.deleteById(id);
    }
}
