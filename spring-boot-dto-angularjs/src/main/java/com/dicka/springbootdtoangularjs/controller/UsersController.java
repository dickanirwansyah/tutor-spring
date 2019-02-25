package com.dicka.springbootdtoangularjs.controller;

import com.dicka.springbootdtoangularjs.dto.UsersDto;
import com.dicka.springbootdtoangularjs.entity.Users;
import com.dicka.springbootdtoangularjs.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/{usersId}")
    public UsersDto getUsersId(@PathVariable("usersId")Integer usersId){
        return usersService.getUsersById(usersId);
    }

    @GetMapping
    public List<UsersDto> getAll(){
        return usersService.getAllUsers();
    }

    @PostMapping
    public void saveUsers(@RequestBody UsersDto usersDto){
        usersService.saveUsers(usersDto);
    }
}
