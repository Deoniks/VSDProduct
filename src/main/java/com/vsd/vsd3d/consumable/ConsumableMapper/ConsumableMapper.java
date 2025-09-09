package com.vsd.vsd3d.consumable.ConsumableMapper;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumableDto;
import com.vsd.vsd3d.consumable.ConsumableEntity.Consumable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumableMapper {
    ConsumableDto toDto(Consumable entity);

    Consumable entity(ConsumableDto dto);

}
