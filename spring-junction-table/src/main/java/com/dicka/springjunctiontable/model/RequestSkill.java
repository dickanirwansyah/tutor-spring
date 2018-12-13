package com.dicka.springjunctiontable.model;

import javax.validation.constraints.NotBlank;

public class RequestSkill {

    @NotBlank(message = "skill id masih kosong")
    private String skillId;

    @NotBlank(message = "nama masih kosong")
    private String nama;

    public RequestSkill(){}

    public RequestSkill(String skillId, String nama){
        this.skillId = skillId;
        this.nama = nama;
    }

    public String getSkillId(){
        return skillId;
    }

    public void setSkillId(String skillId){
        this.skillId = skillId;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }
}
