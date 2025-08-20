package com.vsd.vsd3d.product.ProductService;

import com.vsd.vsd3d.product.ProductDto.CategoryDto;
import com.vsd.vsd3d.product.ProductMapper.CategoryMapper;
import com.vsd.vsd3d.product.ProductRepository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<CategoryDto>getAll(){
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
}
