package com.vsd.vsd3d.mapper;

import com.vsd.vsd3d.domain.Users;
import com.vsd.vsd3d.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Users user);
    Users toEntity(UserDto dto);
    List<UserDto>toDtoList(List<Users>users);

}
