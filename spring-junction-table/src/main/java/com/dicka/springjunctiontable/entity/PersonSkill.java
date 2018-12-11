package com.dicka.springjunctiontable.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "person_skill")
public class PersonSkill implements Serializable{

    @Id
    private PersonSkillPK personSkillPK;

    public PersonSkill(){
        this.personSkillPK = new PersonSkillPK();
    }

    public PersonSkillPK getPersonSkillPK() {
        return personSkillPK;
    }

    public void setPersonSkillPK(PersonSkillPK personSkillPK){
        this.personSkillPK = personSkillPK;
    }
}
