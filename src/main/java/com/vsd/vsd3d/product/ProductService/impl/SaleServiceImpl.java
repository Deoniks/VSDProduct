package com.vsd.vsd3d.product.ProductService.impl;

import com.vsd.vsd3d.exception.NotFoundException;
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
        Sale entity = mapper.toEntity(dto);
        entity.setProduct(product);
        return mapper.toDto(saleRepo.save(entity));
    }

    @Override
    @Transactional
    public SaleDto update(Long id, SaleDto dto) {
        Sale existing = saleRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Sale not found"));
        if (!existing.getProduct().getId().equals(dto.getProductId())) {
            Product product = productRepo.findById(dto.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            existing.setProduct(product);
        }
        existing.setDate(dto.getDate());
        existing.setQuantity(dto.getQuantity());
        existing.setSalePrice(dto.getSalePrice());
        existing.setTaxPercent(dto.getTaxPercent());
        existing.setCommissionPercent(dto.getCommissionPercent());
        existing.setSource(dto.getSource());
        existing.setComment(dto.getComment());
        return mapper.toDto(saleRepo.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!saleRepo.existsById(id)) throw new NotFoundException("Sale not found");
        saleRepo.deleteById(id);
    }
}
