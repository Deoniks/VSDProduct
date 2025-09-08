package com.vsd.vsd3d.product.ProductMapper;

import com.vsd.vsd3d.product.ProductEntity.Product;
import com.vsd.vsd3d.product.ProductDto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDto toDto(Product product);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt",expression = "java(java.time.LocalDateTime.now())")
    Product enitity(ProductDto dto);
}
