package com.vsd.vsd3d.product.ProductService;

import com.vsd.vsd3d.product.ProductDto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    ProductDto create (ProductDto dto);
    ProductDto update (Long id, ProductDto dto);
    void delete(Long id);
}
