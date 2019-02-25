package com.dicka.springbootdtoangularjs.converter;

import com.dicka.springbootdtoangularjs.dto.UsersDto;
import com.dicka.springbootdtoangularjs.entity.Users;

import java.util.stream.Collectors;

public class UsersConverter {

    public static Users dtoToEntity(UsersDto usersDto){
        Users users = new Users(usersDto.getUsersName(), null);
        users.setUsersId(usersDto.getUsersId());
        users.setSkills(usersDto.getSkillDtos()
                .stream().map(SkillConverter::dtoToEntity)
                .collect(Collectors.toList()));
        return users;
    }

    public static UsersDto entityToDto(Users users){
        UsersDto usersDto = new UsersDto(users.getUsersId(), users.getName(), null);
        usersDto.setSkillDtos(users.getSkills()
        .stream().map(SkillConverter::entityToDto)
        .collect(Collectors.toList()));
        return usersDto;
    }

}
