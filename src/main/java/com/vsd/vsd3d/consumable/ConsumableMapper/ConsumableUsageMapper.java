package com.vsd.vsd3d.consumable.ConsumableMapper;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumableUsageDto;
import com.vsd.vsd3d.consumable.ConsumableEntity.ConsumableUsage;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsumableUsageMapper {
    @Mapping(source = "consumable.id", target = "consumableId")
    ConsumableUsageDto toDto(ConsumableUsage entity);

    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "consumable", ignore = true)
    ConsumableUsage toEntity(ConsumableUsageDto d);
}
