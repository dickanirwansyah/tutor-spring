package com.dicka.springbootdtoangularjs.converter;

import com.dicka.springbootdtoangularjs.dto.SkillDto;
import com.dicka.springbootdtoangularjs.entity.Skill;

public class SkillConverter {

    public static Skill dtoToEntity(SkillDto skillDto){
        Skill skill = new Skill(skillDto.getSkillName());
        skill.setSkillId(skillDto.getSkillId());
        return skill;
    }

    public static SkillDto entityToDto(Skill skill){
        return new SkillDto(skill.getSkillId(), skill.getName());
    }
}
