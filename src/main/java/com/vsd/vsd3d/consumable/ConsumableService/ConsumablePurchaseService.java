package com.vsd.vsd3d.consumable.ConsumableService;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumablePurchaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsumablePurchaseService {
    Page<ConsumablePurchaseDto> getAll(Pageable pageable);
    ConsumablePurchaseDto getById(Long id);
    ConsumablePurchaseDto create(ConsumablePurchaseDto dto);
    ConsumablePurchaseDto update(Long id, ConsumablePurchaseDto dto);
    void delete(Long id);
}
