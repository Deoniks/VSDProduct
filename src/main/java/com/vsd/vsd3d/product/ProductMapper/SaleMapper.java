package com.vsd.vsd3d.product.ProductMapper;

import com.vsd.vsd3d.product.ProductDto.SaleDto;
import com.vsd.vsd3d.product.ProductEntity.Sale;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(source = "product.id", target = "productId")
    SaleDto toDto(Sale entity);

    @InheritInverseConfiguration(name = "toDto")
    @Mapping(target = "product", ignore = true)
    Sale toEntity(SaleDto dto);
}
