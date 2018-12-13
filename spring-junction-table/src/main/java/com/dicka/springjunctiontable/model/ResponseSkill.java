package com.dicka.springjunctiontable.model;

import com.dicka.springjunctiontable.entity.Skill;

public class ResponseSkill {

    private String msg;
    private String code;
    private Skill skill;

    public ResponseSkill(String msg, String code, Skill skill){
        this.msg = msg;
        this.code = code;
        this.skill = skill;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public Skill getSkill(){
        return skill;
    }

    public void setSkill(Skill skill){
        this.skill = skill;
    }
}
