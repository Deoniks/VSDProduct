package com.vsd.vsd3d.product.ProductController;

import com.vsd.vsd3d.product.ProductDto.CategoryDto;
import com.vsd.vsd3d.product.ProductService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<CategoryDto> getAll(){
        return service.getAll();
    }
}
