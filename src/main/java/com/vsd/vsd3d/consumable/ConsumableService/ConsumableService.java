package com.vsd.vsd3d.consumable.ConsumableService;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumableService {
    Page<ConsumableDto> getAll(Pageable pageable);
    ConsumableDto getById(Long id);
    ConsumableDto create(ConsumableDto dto);
    ConsumableDto update(Long id, ConsumableDto dto);
    void delete(Long id);
}
