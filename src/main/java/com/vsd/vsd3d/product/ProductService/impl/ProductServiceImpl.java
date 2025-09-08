package com.vsd.vsd3d.product.ProductService.impl;

import com.vsd.vsd3d.exception.GlobalExceptionHandler;
import com.vsd.vsd3d.exception.NotFoundException;
import com.vsd.vsd3d.product.ProductDto.ProductDto;
import com.vsd.vsd3d.product.ProductEntity.Product;
import com.vsd.vsd3d.product.ProductMapper.ProductMapper;
import com.vsd.vsd3d.product.ProductRepository.CategoryRepository;
import com.vsd.vsd3d.product.ProductRepository.ProductRepository;
import com.vsd.vsd3d.product.ProductService.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    @Override
    public List<ProductDto> getAll() {
        return productRepo.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));
        return null;
    }

    @Override
    public ProductDto create(ProductDto dto) {
        return null;
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
