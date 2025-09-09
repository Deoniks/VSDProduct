package com.vsd.vsd3d.consumable.ConsumableController;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumableUsageDto;
import com.vsd.vsd3d.consumable.ConsumableService.ConsumableUsageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumable-usage")
@RequiredArgsConstructor
public class ConsumableUsageController {
    private final ConsumableUsageService service;

    @GetMapping
    public Page<ConsumableUsageDto> getAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size) {
        return service.getAll(PageRequest.of(page, size, Sort.by("date").descending()));
    }

    @GetMapping("/{id}")
    public ConsumableUsageDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ConsumableUsageDto create(@Valid @RequestBody ConsumableUsageDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ConsumableUsageDto update(@PathVariable Long id, @Valid @RequestBody ConsumableUsageDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
