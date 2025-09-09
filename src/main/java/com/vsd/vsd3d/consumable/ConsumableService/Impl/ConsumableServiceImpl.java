package com.vsd.vsd3d.consumable.ConsumableService.Impl;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumableDto;
import com.vsd.vsd3d.consumable.ConsumableEntity.Consumable;
import com.vsd.vsd3d.consumable.ConsumableMapper.ConsumableMapper;
import com.vsd.vsd3d.consumable.ConsumableRepository.ConsumableRepository;
import com.vsd.vsd3d.consumable.ConsumableService.ConsumableService;
import com.vsd.vsd3d.exception.AlreadyExistsException;
import com.vsd.vsd3d.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumableServiceImpl implements ConsumableService {

    private final ConsumableRepository repository;
    private final ConsumableMapper mapper;

    @Override
    public Page<ConsumableDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public ConsumableDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Consumable not found"));
    }

    @Override
    @Transactional
    public ConsumableDto create(ConsumableDto dto) {
        if (repository.existsBySku(dto.getSku())) {
            throw new AlreadyExistsException("Consumable with SKU already exists");
        }
        Consumable e = mapper.entity(dto);
        return mapper.toDto(repository.save(e));
    }

    @Override
    @Transactional
    public ConsumableDto update(Long id, ConsumableDto dto) {
        Consumable e = repository.findById(id).orElseThrow(() -> new NotFoundException("Consumable not found"));
        if (!e.getSku().equals(dto.getSku()) && repository.existsBySku(dto.getSku())) {
            throw new AlreadyExistsException("Consumable with SKU already exists");
        }
        e.setName(dto.getName());
        e.setUnit(dto.getUnit());
        e.setSku(dto.getSku());
        e.setDescription(dto.getDescription());
        return mapper.toDto(repository.save(e));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException("Consumable not found");
        repository.deleteById(id);
    }
}
