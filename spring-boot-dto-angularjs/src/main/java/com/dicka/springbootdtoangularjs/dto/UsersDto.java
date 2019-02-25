package com.dicka.springbootdtoangularjs.dto;

import java.util.ArrayList;
import java.util.List;

public class UsersDto {

    private Integer usersId;
    private String usersName;
    private List<SkillDto> skillDtos = new ArrayList<>();

    public UsersDto(){}

    public UsersDto(Integer usersId, String usersName, List<SkillDto> skillDtos){
        this.usersId = usersId;
        this.usersName = usersName;
        this.skillDtos = skillDtos;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId){
        this.usersId = usersId;
    }

    public String getUsersName(){
        return usersName;
    }

    public void setUsersName(String usersName){
        this.usersName = usersName;
    }

    public List<SkillDto> getSkillDtos(){
        return skillDtos;
    }

    public void setSkillDtos(List<SkillDto> skillDtos){
        this.skillDtos = skillDtos;
    }
}
