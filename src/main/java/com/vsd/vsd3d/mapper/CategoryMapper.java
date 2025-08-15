package com.vsd.vsd3d.mapper;

import com.vsd.vsd3d.domain.Category;
import com.vsd.vsd3d.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto dto);
    CategoryDto toDto(Category entity);
}
