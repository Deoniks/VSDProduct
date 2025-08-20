package com.vsd.vsd3d.product.ProductMapper;

import com.vsd.vsd3d.product.ProductDto.UserDto;
import com.vsd.vsd3d.product.ProductEntity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Users user);
    Users toEntity(UserDto dto);
    List<UserDto>toDtoList(List<Users>users);

}
