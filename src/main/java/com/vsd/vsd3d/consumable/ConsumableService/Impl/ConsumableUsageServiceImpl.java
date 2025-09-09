package com.vsd.vsd3d.consumable.ConsumableService.Impl;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumableUsageDto;
import com.vsd.vsd3d.consumable.ConsumableEntity.Consumable;
import com.vsd.vsd3d.consumable.ConsumableEntity.ConsumableUsage;
import com.vsd.vsd3d.consumable.ConsumableMapper.ConsumableUsageMapper;
import com.vsd.vsd3d.consumable.ConsumableRepository.ConsumableRepository;
import com.vsd.vsd3d.consumable.ConsumableRepository.ConsumableUsageRepository;
import com.vsd.vsd3d.consumable.ConsumableService.ConsumableUsageService;
import com.vsd.vsd3d.exception.NotFoundException;
import com.vsd.vsd3d.inventory.entity.RefType;
import com.vsd.vsd3d.inventory.service.InventoryMovementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumableUsageServiceImpl implements ConsumableUsageService {
    private final ConsumableUsageRepository usageRepository;
    private final ConsumableRepository consumableRepository;
    private final ConsumableUsageMapper mapper;
    private final InventoryMovementService inventoryService;

    @Override
    public Page<ConsumableUsageDto> getAll(Pageable pageable) {
        return usageRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public ConsumableUsageDto getById(Long id) {
        return usageRepository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Consumable usage not found"));
    }

    @Override
    @Transactional
    public ConsumableUsageDto create(ConsumableUsageDto dto) {
        var c = consumableRepository.findById(dto.getConsumableId())
                .orElseThrow(() -> new NotFoundException("Consumable not found"));

        // ✅ проверяем остаток по ID
        inventoryService.assertEnoughConsumable(dto.getConsumableId(), dto.getQuantity());

        var e = mapper.toEntity(dto);
        e.setConsumable(c);
        var saved = usageRepository.save(e);

        inventoryService.addConsumableOut(
                dto.getConsumableId(), saved.getId(),
                RefType.CONSUMABLE_USE, saved.getQuantity(), saved.getDate()
        );
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ConsumableUsageDto update(Long id, ConsumableUsageDto dto) {
        ConsumableUsage e = usageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consumable usage not found"));

        if (!e.getConsumable().getId().equals(dto.getConsumableId())) {
            Consumable c = consumableRepository.findById(dto.getConsumableId())
                    .orElseThrow(() -> new NotFoundException("Consumable not found"));
            e.setConsumable(c);
        }

        inventoryService.assertEnoughConsumable(dto.getConsumableId(), dto.getQuantity());

        e.setDate(dto.getDate());
        e.setQuantity(dto.getQuantity());
        e.setReason(dto.getReason());
        e.setComment(dto.getComment());

        inventoryService.deleteByRef(RefType.CONSUMABLE_USE, e.getId());
        var saved = usageRepository.save(e);
        inventoryService.addConsumableOut(
                saved.getConsumable().getId(), saved.getId(),
                RefType.CONSUMABLE_USE, saved.getQuantity(), saved.getDate()
        );
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!usageRepository.existsById(id)) throw new NotFoundException("Consumable usage not found");
        inventoryService.deleteByRef(RefType.CONSUMABLE_USE, id);
        usageRepository.deleteById(id);
    }
}
