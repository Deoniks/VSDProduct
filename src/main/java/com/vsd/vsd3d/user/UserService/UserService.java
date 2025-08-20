package com.vsd.vsd3d.user.UserService;


import com.vsd.vsd3d.user.UserDto.UserDto;

import java.util.List;

public interface UserService{
    List<UserDto> getAll();
    UserDto getById(Long Id);
    UserDto create(UserDto dto);
    UserDto update(Long id, UserDto dto);
    void delete(Long Id);
}
