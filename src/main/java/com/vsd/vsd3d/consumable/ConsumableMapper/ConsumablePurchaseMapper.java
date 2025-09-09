package com.vsd.vsd3d.consumable.ConsumableMapper;

import com.vsd.vsd3d.consumable.ConsumableDto.ConsumablePurchaseDto;
import com.vsd.vsd3d.consumable.ConsumableEntity.ConsumablePurchase;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsumablePurchaseMapper {
    @Mapping(source = "consumable.id", target = "consumableId")
    ConsumablePurchaseDto toDto(ConsumablePurchase entity);

    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "consumable", ignore = true)
    ConsumablePurchase toEntity(ConsumablePurchaseDto dto);
}
