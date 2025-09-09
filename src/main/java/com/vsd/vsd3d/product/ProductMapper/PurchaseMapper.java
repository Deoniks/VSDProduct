package com.vsd.vsd3d.product.ProductMapper;

import com.vsd.vsd3d.product.ProductDto.PurchaseDto;
import com.vsd.vsd3d.product.ProductEntity.Purchase;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    @Mapping(source = "product.id", target = "product_id")
    PurchaseDto toDto(Purchase entity);

    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "product", ignore = true)
    Purchase toEntity(PurchaseDto dto);
}
