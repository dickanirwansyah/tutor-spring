package com.dicka.springbootdtoangularjs.service;

import com.dicka.springbootdtoangularjs.dto.UsersDto;

import java.util.List;

public interface UsersService {

    UsersDto getUsersById(Integer usersId);
    void saveUsers(UsersDto usersDto);
    List<UsersDto> getAllUsers();
}
