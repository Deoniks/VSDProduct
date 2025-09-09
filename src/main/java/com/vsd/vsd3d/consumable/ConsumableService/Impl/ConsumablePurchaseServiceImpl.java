package com.vsd.vsd3d.consumable.ConsumableService.Impl;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumableDto;
import com.vsd.vsd3d.consumable.ConsumableDto.ConsumablePurchaseDto;
import com.vsd.vsd3d.consumable.ConsumableEntity.Consumable;
import com.vsd.vsd3d.consumable.ConsumableEntity.ConsumablePurchase;
import com.vsd.vsd3d.consumable.ConsumableMapper.ConsumablePurchaseMapper;
import com.vsd.vsd3d.consumable.ConsumableRepository.ConsumablePurchaseRepository;
import com.vsd.vsd3d.consumable.ConsumableRepository.ConsumableRepository;
import com.vsd.vsd3d.consumable.ConsumableService.ConsumablePurchaseService;
import com.vsd.vsd3d.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumablePurchaseServiceImpl implements ConsumablePurchaseService {
    private final ConsumablePurchaseRepository purchaseRepository;
    private final ConsumableRepository consumableRepository;
    private final ConsumablePurchaseMapper mapper;

    @Override
    public Page<ConsumablePurchaseDto> getAll(Pageable pageable) {
        return purchaseRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public ConsumablePurchaseDto getById(Long id) {
        return purchaseRepository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Consumable purchase not found"));
    }

    @Override
    @Transactional
    public ConsumablePurchaseDto create(ConsumablePurchaseDto dto) {
        Consumable c = consumableRepository.findById(dto.getConsumableId())
                .orElseThrow(() -> new NotFoundException("Consumable not found"));
        ConsumablePurchase e = mapper.toEntity(dto);
        e.setConsumable(c);
        return mapper.toDto(purchaseRepository.save(e));
    }

    @Override
    @Transactional
    public ConsumablePurchaseDto update(Long id, ConsumablePurchaseDto dto) {
        ConsumablePurchase e = purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Consumable purchase not found"));

        if (!e.getConsumable().getId().equals(dto.getConsumableId())) {
            Consumable c = consumableRepository.findById(dto.getConsumableId())
                    .orElseThrow(() -> new NotFoundException("Consumable not found"));
            e.setConsumable(c);
        }
        e.setDate(dto.getDate());
        e.setQuantity(dto.getQuantity());
        e.setUnitPrice(dto.getUnitPrice());
        e.setDeliveryCost(dto.getDeliveryCost());
        e.setComment(dto.getComment());
        return mapper.toDto(purchaseRepository.save(e));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!purchaseRepository.existsById(id)) throw new NotFoundException("Consumable purchase not found");
        purchaseRepository.deleteById(id);
    }
}
