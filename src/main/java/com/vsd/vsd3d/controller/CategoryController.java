package com.vsd.vsd3d.controller;

import com.vsd.vsd3d.dto.CategoryDto;
import com.vsd.vsd3d.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public List<CategoryDto> getAll(){
        return service.getAll();
    }
}
