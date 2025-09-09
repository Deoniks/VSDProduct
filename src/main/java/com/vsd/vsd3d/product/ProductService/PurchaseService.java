package com.vsd.vsd3d.product.ProductService;

import com.vsd.vsd3d.product.ProductDto.PurchaseDto;
import com.vsd.vsd3d.product.ProductEntity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    Page<PurchaseDto> getAll(Pageable pageable);
    PurchaseDto getById(Long id);
    PurchaseDto create(PurchaseDto dto);
    PurchaseDto update(Long id, PurchaseDto dto);
    void delete(Long id);
}
