package com.vsd.vsd3d.consumable.ConsumableService;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumableUsageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumableUsageService {
    Page<ConsumableUsageDto> getAll(Pageable pageable);
    ConsumableUsageDto getById(Long id);
    ConsumableUsageDto create(ConsumableUsageDto dto);
    ConsumableUsageDto update(Long id, ConsumableUsageDto dto);
    void delete(Long id);
}
