package com.vsd.vsd3d.product.ProductMapper;

import com.vsd.vsd3d.product.ProductDto.CategoryDto;
import com.vsd.vsd3d.product.ProductEntity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto dto);
    CategoryDto toDto(Category entity);
}
