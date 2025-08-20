package com.vsd.vsd3d.user.UserMapper;

import com.vsd.vsd3d.user.UserDto.UserDto;
import com.vsd.vsd3d.user.UserEntity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Users user);
    Users toEntity(UserDto dto);
    List<UserDto>toDtoList(List<Users>users);

}
