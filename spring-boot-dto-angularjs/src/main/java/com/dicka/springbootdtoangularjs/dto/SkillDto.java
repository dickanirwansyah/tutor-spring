package com.dicka.springbootdtoangularjs.dto;

public class SkillDto {

    private Integer skillId;
    private String skillName;

    public SkillDto(){}

    public SkillDto(Integer skillId, String skillName){
        this.skillId = skillId;
        this.skillName = skillName;
    }

    public Integer getSkillId(){
        return skillId;
    }

    public void setSkillId(Integer skillId){
        this.skillId = skillId;
    }

    public String getSkillName(){
        return skillName;
    }

    public void setSkillName(String skillName){
        this.skillName = skillName;
    }
}
