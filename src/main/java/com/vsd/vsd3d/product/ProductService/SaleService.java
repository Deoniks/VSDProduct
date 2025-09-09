package com.vsd.vsd3d.product.ProductService;

import com.vsd.vsd3d.product.ProductDto.SaleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleService {
    Page<SaleDto> getAll(Pageable pageable);
    SaleDto getById(Long id);
    SaleDto create(SaleDto dto);
    SaleDto update(Long id, SaleDto dto);
    void delete(Long id);
}
