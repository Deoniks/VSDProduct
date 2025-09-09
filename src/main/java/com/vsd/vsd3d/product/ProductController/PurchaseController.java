package com.vsd.vsd3d.product.ProductController;

import com.vsd.vsd3d.product.ProductDto.PurchaseDto;
import com.vsd.vsd3d.product.ProductService.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService service;

    @GetMapping
    public Page<PurchaseDto> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "20") int size) {
        return service.getAll(PageRequest.of(page, size, Sort.by("date").descending()));
    }

    @GetMapping("/{id}")
    public PurchaseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public PurchaseDto create(@Valid @RequestBody PurchaseDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PurchaseDto update(@PathVariable Long id, @Valid @RequestBody PurchaseDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
