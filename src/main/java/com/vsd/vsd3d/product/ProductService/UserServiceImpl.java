package com.vsd.vsd3d.product.ProductService;

import com.vsd.vsd3d.product.ProductDto.UserDto;
import com.vsd.vsd3d.product.ProductRepository.UserRepository;
import com.vsd.vsd3d.product.ProductEntity.Users;
import com.vsd.vsd3d.product.ProductMapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public List<UserDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public UserDto getById(Long Id) {
        return repository.findById(Id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public UserDto create(UserDto dto) {
        Users user = mapper.toEntity(dto);
        user.setPassword("default");
        return mapper.toDto(repository.save(user));
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        Users user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return mapper.toDto(repository.save(user));
    }

    @Override
    public void delete(Long Id) {
        repository.deleteById(Id);
    }
}
