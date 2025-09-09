package com.vsd.vsd3d.consumable.ConsumableController;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumableDto;
import com.vsd.vsd3d.consumable.ConsumableService.ConsumableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumables")
@RequiredArgsConstructor
public class ConsumableController {
    private final ConsumableService service;

    @GetMapping()
    public Page<ConsumableDto> getAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "20") int size){
        return service.getAll(PageRequest.of(page,size, Sort.by("name").ascending()));
    }

    @GetMapping("/{id}")
    public ConsumableDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public ConsumableDto create(@Valid @RequestBody ConsumableDto dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ConsumableDto update(@PathVariable Long id, @Valid @RequestBody ConsumableDto dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long Id){
        service.delete(Id);
    }
}
