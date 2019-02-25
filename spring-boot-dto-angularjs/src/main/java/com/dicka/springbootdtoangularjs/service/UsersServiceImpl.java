package com.dicka.springbootdtoangularjs.service;

import com.dicka.springbootdtoangularjs.converter.UsersConverter;
import com.dicka.springbootdtoangularjs.dto.UsersDto;
import com.dicka.springbootdtoangularjs.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersDto getUsersById(Integer usersId) {
        return UsersConverter.entityToDto(usersRepository.getOne(usersId));
    }

    @Override
    public void saveUsers(UsersDto usersDto) {
        usersRepository.save(UsersConverter.dtoToEntity(usersDto));
    }

    @Override
    public List<UsersDto> getAllUsers() {
        return usersRepository.findAll()
                .stream()
                .map(UsersConverter::entityToDto)
                .collect(Collectors.toList());
    }
}
