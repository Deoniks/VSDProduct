package com.vsd.vsd3d.consumable.ConsumableController;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumablePurchaseDto;
import com.vsd.vsd3d.consumable.ConsumableService.ConsumablePurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumable-purchase")
@RequiredArgsConstructor
public class ConsumablePurchaseController {
    private final ConsumablePurchaseService service;

    @GetMapping
    public Page<ConsumablePurchaseDto> getAll(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "20") int size){
        return service.getAll(PageRequest.of(page, size, Sort.by("date").descending()));
    }

    @GetMapping("/{id}")
    public ConsumablePurchaseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ConsumablePurchaseDto create(@Valid @RequestBody ConsumablePurchaseDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ConsumablePurchaseDto update(@PathVariable Long id, @Valid @RequestBody ConsumablePurchaseDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }//upd
}
