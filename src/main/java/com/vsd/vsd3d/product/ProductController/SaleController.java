package com.vsd.vsd3d.product.ProductController;

import com.vsd.vsd3d.product.ProductDto.SaleDto;
import com.vsd.vsd3d.product.ProductService.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;


    @GetMapping
    public Page<SaleDto> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "20") int size) {
        return service.getAll(PageRequest.of(page, size, Sort.by("date").descending()));
    }

    @GetMapping("/{id}")
    public SaleDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public SaleDto create(@Valid @RequestBody SaleDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public SaleDto update(@PathVariable Long id, @Valid @RequestBody SaleDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
