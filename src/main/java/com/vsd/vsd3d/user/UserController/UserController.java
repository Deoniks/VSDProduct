package com.vsd.vsd3d.user.UserController;

import com.vsd.vsd3d.user.UserDto.UserDto;
import com.vsd.vsd3d.user.UserService.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;

    @GetMapping
    public List<UserDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto){
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto){
        return service.update(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
