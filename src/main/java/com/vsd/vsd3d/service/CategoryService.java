package com.vsd.vsd3d.service;

import com.vsd.vsd3d.dto.CategoryDto;
import com.vsd.vsd3d.mapper.CategoryMapper;
import com.vsd.vsd3d.repository.CategoryRepository;
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
